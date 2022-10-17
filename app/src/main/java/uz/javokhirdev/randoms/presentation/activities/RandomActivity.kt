package uz.javokhirdev.randoms.presentation.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import uz.javokhirdev.randoms.core.extensions.*
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.RandomModel
import uz.javokhirdev.randoms.data.onFailure
import uz.javokhirdev.randoms.data.onLoading
import uz.javokhirdev.randoms.data.onSuccess
import uz.javokhirdev.randoms.databinding.ActivityRandomBinding
import uz.javokhirdev.randoms.presentation.navigation.NavArguments
import uz.javokhirdev.randoms.presentation.viewmodels.RandomViewModel

@AndroidEntryPoint
class RandomActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRandomBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<RandomViewModel>()

    private var randomText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)

        with(binding) {
            layoutActions.insetsPadding(24)

            toolbar.title = NavArguments.model?.title.orEmpty()
            toolbar.setNavigationOnClickListener { finish() }

            buttonRefresh.onClick {
                randomText = ""
                viewModel.getRandom()
            }
            buttonShare.onClick { if (randomText.isNotEmpty()) shareText(randomText) }
        }

        viewModel.random.observe(this) { onRandom(it) }
    }

    private fun onRandom(uiState: UIState<RandomModel>) {
        with(binding) {
            progressBar.beGone()
            textTitle.beGone()
            textSubtitle.beGone()

            uiState onLoading {
                progressBar.beVisible()
            } onSuccess {
                with(binding) {
                    textTitle.beVisibleIf(!data?.title.isNullOrEmpty())
                    textTitle.text = data?.title.orEmpty()

                    textSubtitle.beVisibleIf(!data?.subtitle.isNullOrEmpty())
                    textSubtitle.text = data?.subtitle.orEmpty()

                    randomText = "${data?.title.orEmpty()}\n\n${data?.subtitle.orEmpty()}"
                }
            } onFailure {
                textTitle.text = message
                textTitle.beVisible()

                randomText = ""
            }
        }
    }
}