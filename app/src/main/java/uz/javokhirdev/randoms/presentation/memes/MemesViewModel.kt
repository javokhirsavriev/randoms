package uz.javokhirdev.randoms.presentation.memes

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
class MemesViewModel @Inject constructor() : ViewModel() {

    private val uiStateData = MutableStateFlow(emptyList<ListModel>())
    val uiState = uiStateData.asStateFlow()

    init {
        getMemes()
    }

    private fun getMemes() {
        viewModelScope.launch {
            uiStateData.update { memes }
        }
    }

    private val memes
        get() = listOf(
            ListModel(
                id = 1,
                title = "Dad jokes",
                cover = "file:///android_asset/memes/image_01.webp"
            ),
            ListModel(
                id = 2,
                title = "Chuck Norris jokes",
                cover = "file:///android_asset/memes/image_02.webp"
            ),
            ListModel(
                id = 3,
                title = "Yo Momma jokes",
                cover = "file:///android_asset/memes/image_03.webp"
            ),
            ListModel(
                id = 4,
                title = "Programming jokes",
                cover = "file:///android_asset/memes/image_04.webp"
            ),
            ListModel(
                id = 5,
                title = "Dev jokes",
                cover = "file:///android_asset/memes/image_05.webp"
            )
        )
}