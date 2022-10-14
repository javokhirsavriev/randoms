package uz.javokhirdev.randoms.presentation.joke

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    private val viewModel by viewModels<JokeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            imageJoke.loadImage(NavArguments.listModel?.cover)

            buttonBack.onClick { finish() }
            buttonRefresh.onClick { viewModel.getJoke() }
            buttonShare.onClick { shareText("") }
        }

        repeatingJobOnStarted {
            viewModel.uiState.collectLatest { onUiState(it) }
        }
    }

    private fun onUiState(uiState: UIState<JokeModel>) {
        uiState onSuccess {

        } onFailure {
            toast(message)
        }
    }
}