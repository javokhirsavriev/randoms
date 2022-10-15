package uz.javokhirdev.randoms.presentation.jokes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.javokhirdev.randoms.core.extensions.*
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.JokeModel
import uz.javokhirdev.randoms.data.onFailure
import uz.javokhirdev.randoms.data.onSuccess
import uz.javokhirdev.randoms.databinding.ActivityJokeBinding
import uz.javokhirdev.randoms.presentation.navigation.NavArguments

@AndroidEntryPoint
class JokeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityJokeBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<JokesViewModel>()

    private var joke: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)

        with(binding) {
            layoutActions.insetsPadding(24)

            toolbar.title = NavArguments.model?.title.orEmpty()
            toolbar.setNavigationOnClickListener { finish() }

            buttonRefresh.onClick { viewModel.getJoke() }
            buttonShare.onClick { shareText(joke) }
        }

        with(viewModel) {
            getJoke()

            repeatingJobOnStarted { joke.collectLatest { onJoke(it) } }
        }
    }

    private fun onJoke(uiState: UIState<JokeModel>) {
        uiState onSuccess {
            with(binding) {
                textQuestion.beVisibleIf(!data?.question.isNullOrEmpty())
                textQuestion.text = data?.question.orEmpty()

                textPunchline.beVisibleIf(!data?.punchline.isNullOrEmpty())
                textPunchline.text = data?.punchline.orEmpty()

                joke = "${data?.question.orEmpty()}\n\n${data?.punchline.orEmpty()}"
            }
        } onFailure {
            toast(message)
        }
    }
}