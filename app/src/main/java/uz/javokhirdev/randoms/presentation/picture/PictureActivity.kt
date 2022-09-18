package uz.javokhirdev.randoms.presentation.picture

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import uz.javokhirdev.randoms.core.extensions.loadImage
import uz.javokhirdev.randoms.core.extensions.onClick
import uz.javokhirdev.randoms.core.extensions.toast
import uz.javokhirdev.randoms.core.helpers.DownloaderManager
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.onFailure
import uz.javokhirdev.randoms.data.onSuccess
import uz.javokhirdev.randoms.databinding.ActivityPictureBinding
import uz.javokhirdev.randoms.presentation.navigation.NavArguments
import javax.inject.Inject

@AndroidEntryPoint
class PictureActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPictureBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<PictureViewModel>()

    @Inject
    lateinit var downloaderManager: DownloaderManager

    private var downloadUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            buttonBack.onClick { finish() }
            buttonRefresh.onClick { viewModel.getRandomImage() }
            buttonDownload.onClick { downloaderManager.download(downloadUrl) }
        }

        viewModel.uiState.observe(this) { onUiState(it) }
    }

    private fun onUiState(uiState: UIState<String>) {
        uiState onSuccess {
            downloadUrl = data
            binding.imageRandom.loadImage(
                url = data,
                skipMemoryCache = NavArguments.listModel?.isImageUrl ?: false
            )
        } onFailure {
            toast(message)
        }
    }
}