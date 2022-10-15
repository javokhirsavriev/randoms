package uz.javokhirdev.randoms.presentation.pictures

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import uz.javokhirdev.randoms.core.extensions.insetsPadding
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

    private val viewModel by viewModels<PicturesViewModel>()

    @Inject
    lateinit var downloaderManager: DownloaderManager

    private var downloadUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            layoutActions.insetsPadding(24)

            toolbar.title = NavArguments.model?.title.orEmpty()
            toolbar.setNavigationOnClickListener { finish() }

            buttonRefresh.onClick { viewModel.getRandomImage() }
            buttonDownload.onClick { downloaderManager.download(downloadUrl) }
        }

        with(viewModel) {
            getRandomImage()

            randomImage.observe(this@PictureActivity) { onRandomImage(it) }
        }
    }

    private fun onRandomImage(uiState: UIState<String>) {
        uiState onSuccess {
            downloadUrl = data
            binding.imageRandom.loadImage(
                url = data,
                skipMemoryCache = NavArguments.model?.isImageUrl ?: false
            )
        } onFailure {
            toast(message)
        }
    }
}