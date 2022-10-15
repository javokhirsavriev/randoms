package uz.javokhirdev.randoms.presentation.pictures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.ListModel
import uz.javokhirdev.randoms.domain.UseCases
import uz.javokhirdev.randoms.presentation.navigation.NavArguments
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val uiStateData = MutableStateFlow(emptyList<ListModel>())
    val uiState = uiStateData.asStateFlow()

    private val randomImageData = MutableLiveData<UIState<String>>()
    val randomImage: LiveData<UIState<String>> = randomImageData

    fun getPictures() {
        viewModelScope.launch { uiStateData.value = pictures }
    }

    fun getRandomImage() {
        val model = NavArguments.model ?: return

        viewModelScope.launch {
            if (model.isImageUrl) {
                randomImageData.value = UIState.success(model.baseUrl)
            } else {
                useCases.getRandomImage(model.baseUrl.orEmpty()).collectLatest {
                    randomImageData.value = it
                }
            }
        }
    }

    private val pictures
        get() = listOf(
            ListModel(
                id = 1,
                title = "Cats pictures and gifs",
                cover = "file:///android_asset/pictures/image_01.webp",
                baseUrl = "https://cataas.com/cat",
                isImageUrl = true
            ),
            ListModel(
                id = 3,
                title = "Pictures of dogs",
                cover = "file:///android_asset/pictures/image_03.webp",
                baseUrl = "https://random.dog/woof.json"
            ),
            ListModel(
                id = 4,
                title = "Pictures of ducks",
                cover = "file:///android_asset/pictures/image_04.webp",
                baseUrl = "https://random-d.uk/api/v2/random"
            ),
            ListModel(
                id = 5,
                title = "Pictures of foxes",
                cover = "file:///android_asset/pictures/image_05.webp",
                baseUrl = "https://randomfox.ca/floof/"
            ),
            ListModel(
                id = 6,
                title = "Pictures of zoo animals",
                cover = "file:///android_asset/pictures/image_06.webp",
                baseUrl = "https://zoo-animal-api.herokuapp.com/animals/rand"
            ),
            ListModel(
                id = 7,
                title = "Pictures of coffee",
                cover = "file:///android_asset/pictures/image_07.webp",
                baseUrl = "https://coffee.alexflipnote.dev/random.json"
            ),
            ListModel(
                id = 8,
                title = "Pictures of food dishes",
                cover = "file:///android_asset/pictures/image_08.webp",
                baseUrl = "https://foodish-api.herokuapp.com/api"
            ),
            ListModel(
                id = 9,
                title = "Neko images, funny GIFs & more",
                cover = "file:///android_asset/pictures/image_09.webp",
                baseUrl = "https://api.catboys.com/img"
            ),
            ListModel(
                id = 11,
                title = "Biriyani images",
                cover = "file:///android_asset/pictures/image_11.webp",
                baseUrl = "https://biriyani.anoram.com/get"
            ),
            ListModel(
                id = 12,
                title = "Images from Unsplash",
                cover = "file:///android_asset/pictures/image_12.webp",
                baseUrl = "https://picsum.photos/1080",
                isImageUrl = true
            )
        )
}