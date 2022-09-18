package uz.javokhirdev.randoms.presentation.pictures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.javokhirdev.randoms.data.model.ListModel
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor() : ViewModel() {

    private val uiStateData = MutableStateFlow(emptyList<ListModel>())
    val uiState = uiStateData.asStateFlow()

    init {
        getPictures()
    }

    private fun getPictures() {
        viewModelScope.launch {
            uiStateData.update { pictures }
        }
    }

    private val pictures
        get() = listOf(
            ListModel(
                id = 1,
                title = "Cats pictures and gifs",
                cover = "file:///android_asset/pictures/image_01.webp"
            ),
            ListModel(
                id = 2,
                title = "Pictures of bears",
                cover = "file:///android_asset/pictures/image_02.webp"
            ),
            ListModel(
                id = 3,
                title = "Pictures of dogs",
                cover = "file:///android_asset/pictures/image_03.webp"
            ),
            ListModel(
                id = 4,
                title = "Pictures of ducks",
                cover = "file:///android_asset/pictures/image_04.webp"
            ),
            ListModel(
                id = 5,
                title = "Pictures of foxes",
                cover = "file:///android_asset/pictures/image_05.webp"
            ),
            ListModel(
                id = 6,
                title = "Pictures of zoo animals",
                cover = "file:///android_asset/pictures/image_06.webp"
            ),
            ListModel(
                id = 7,
                title = "Pictures of coffee",
                cover = "file:///android_asset/pictures/image_07.webp"
            ),
            ListModel(
                id = 8,
                title = "Pictures of food dishes",
                cover = "file:///android_asset/pictures/image_08.webp"
            ),
            ListModel(
                id = 9,
                title = "Neko images, funny GIFs & more",
                cover = "file:///android_asset/pictures/image_09.webp"
            ),
            ListModel(
                id = 10,
                title = "Rick and Morty images",
                cover = "file:///android_asset/pictures/image_10.webp"
            ),
            ListModel(
                id = 11,
                title = "Biriyani images",
                cover = "file:///android_asset/pictures/image_11.webp"
            ),
            ListModel(
                id = 12,
                title = "Images from Unsplash",
                cover = "file:///android_asset/pictures/image_12.webp"
            )
        )
}