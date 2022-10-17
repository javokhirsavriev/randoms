package uz.javokhirdev.randoms.presentation.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import uz.javokhirdev.randoms.core.extensions.*
import uz.javokhirdev.randoms.core.helpers.DownloaderManager
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.RandomModel
import uz.javokhirdev.randoms.data.onFailure
import uz.javokhirdev.randoms.data.onLoading
import uz.javokhirdev.randoms.data.onSuccess
import uz.javokhirdev.randoms.databinding.ActivityPictureBinding
import uz.javokhirdev.randoms.presentation.navigation.NavArguments
import uz.javokhirdev.randoms.presentation.viewmodels.RandomViewModel
import javax.inject.Inject

@AndroidEntryPoint
class PictureActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPictureBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<RandomViewModel>()

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

            buttonRefresh.onClick {
                downloadUrl = ""
                viewModel.getRandom()
            }
            buttonDownload.onClick {
                if (!downloadUrl.isNullOrEmpty()) downloaderManager.download(downloadUrl)
            }
        }

        viewModel.random.observe(this) { onRandomImage(it) }
    }

    private fun onRandomImage(uiState: UIState<RandomModel>) {
        with(binding) {
            progressBar.beGone()
            imageRandom.beGone()

            uiState onLoading {
                progressBar.beVisible()
            } onSuccess {
                downloadUrl = data?.imageUrl

                imageRandom.loadImage(
                    url = data?.imageUrl,
                    skipMemoryCache = NavArguments.model?.isImageUrl ?: false
                )
                imageRandom.beVisible()
            } onFailure {
                toast(message)
            }
        }
    }
}