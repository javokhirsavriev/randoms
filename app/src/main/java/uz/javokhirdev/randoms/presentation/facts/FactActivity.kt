package uz.javokhirdev.randoms.presentation.facts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.javokhirdev.randoms.core.extensions.*
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.onFailure
import uz.javokhirdev.randoms.data.onSuccess
import uz.javokhirdev.randoms.databinding.ActivityFactBinding
import uz.javokhirdev.randoms.presentation.navigation.NavArguments

@AndroidEntryPoint
class FactActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFactBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<FactsViewModel>()

    private var fact: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)

        with(binding) {
            layoutActions.insetsPadding(24)

            toolbar.title = NavArguments.model?.title.orEmpty()
            toolbar.setNavigationOnClickListener { finish() }

            buttonRefresh.onClick { viewModel.getFact() }
            buttonShare.onClick { shareText(fact) }
        }

        with(viewModel) {
            getFact()

            repeatingJobOnStarted { fact.collectLatest { onFact(it) } }
        }
    }

    private fun onFact(uiState: UIState<String>) {
        uiState onSuccess {
            with(binding) {
                textFact.beVisibleIf(!data.isNullOrEmpty())
                textFact.text = data.orEmpty()

                fact = "${data.orEmpty()}}"
            }
        } onFailure {
            toast(message)
        }
    }
}