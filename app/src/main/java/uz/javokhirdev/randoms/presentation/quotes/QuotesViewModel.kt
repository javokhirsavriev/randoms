package uz.javokhirdev.randoms.presentation.quotes

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
class QuotesViewModel @Inject constructor() : ViewModel() {

    private val uiStateData = MutableStateFlow(emptyList<ListModel>())
    val uiState = uiStateData.asStateFlow()

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            uiStateData.update { quotes }
        }
    }

    private val quotes
        get() = listOf(
            ListModel(
                id = 1,
                title = "Anime quotes",
                cover = "file:///android_asset/quotes/image_01.webp"
            ),
            ListModel(
                id = 2,
                title = "Inspirational quotes",
                cover = "file:///android_asset/quotes/image_02.webp"
            ),
            ListModel(
                id = 3,
                title = "Motivational quotes",
                cover = "file:///android_asset/quotes/image_03.webp"
            ),
            ListModel(
                id = 4,
                title = "Kanye West quotes",
                cover = "file:///android_asset/quotes/image_04.webp"
            ),
            ListModel(
                id = 5,
                title = "Kimi Räikkönen quotes",
                cover = "file:///android_asset/quotes/image_05.webp"
            ),
            ListModel(
                id = 6,
                title = "Ron Swanson quotes",
                cover = "file:///android_asset/quotes/image_06.webp"
            ),
            ListModel(
                id = 7,
                title = "Game of Thrones quotes",
                cover = "file:///android_asset/quotes/image_07.webp"
            ),
            ListModel(
                id = 8,
                title = "Breaking Bad quotes",
                cover = "file:///android_asset/quotes/image_08.webp"
            ),
            ListModel(
                id = 9,
                title = "Stranger Things quotes",
                cover = "file:///android_asset/quotes/image_09.webp"
            ),
            ListModel(
                id = 10,
                title = "Friends quotes",
                cover = "file:///android_asset/quotes/image_10.webp"
            ),
            ListModel(
                id = 11,
                title = "Lucifer quotes",
                cover = "file:///android_asset/quotes/image_11.webp"
            ),
            ListModel(
                id = 12,
                title = "Zen quotes",
                cover = "file:///android_asset/quotes/image_12.webp"
            ),
            ListModel(
                id = 13,
                title = "Programming quotes",
                cover = "file:///android_asset/quotes/image_13.webp"
            ),
            ListModel(
                id = 14,
                title = "Code quotes",
                cover = "file:///android_asset/quotes/image_14.webp"
            )
        )
}