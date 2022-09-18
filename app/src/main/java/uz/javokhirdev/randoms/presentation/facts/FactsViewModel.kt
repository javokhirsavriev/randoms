package uz.javokhirdev.randoms.presentation.facts

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
class FactsViewModel @Inject constructor() : ViewModel() {

    private val uiStateData = MutableStateFlow(emptyList<ListModel>())
    val uiState = uiStateData.asStateFlow()

    init {
        getFacts()
    }

    private fun getFacts() {
        viewModelScope.launch {
            uiStateData.update { facts }
        }
    }

    private val facts
        get() = listOf(
            ListModel(
                id = 1,
                title = "Facts of cats",
                cover = "file:///android_asset/facts/image_01.webp"
            ),
            ListModel(
                id = 2,
                title = "Facts of dogs",
                cover = "file:///android_asset/facts/image_02.webp"
            ),
            ListModel(
                id = 3,
                title = "Anime facts",
                cover = "file:///android_asset/facts/image_03.webp"
            ),
            ListModel(
                id = 4,
                title = "Useless, but true facts",
                cover = "file:///android_asset/facts/image_04.webp"
            ),
            ListModel(
                id = 5,
                title = "Fun facts",
                cover = "file:///android_asset/facts/image_05.webp"
            ),
            ListModel(
                id = 6,
                title = "Facts about numbers",
                cover = "file:///android_asset/facts/image_06.webp"
            ),
            ListModel(
                id = 7,
                title = "Chuck Norris facts",
                cover = "file:///android_asset/facts/image_07.webp"
            ),
            ListModel(
                id = 8,
                title = "Knowledge facts",
                cover = "file:///android_asset/facts/image_08.webp"
            ),
            ListModel(
                id = 9,
                title = "Random facts",
                cover = "file:///android_asset/facts/image_09.webp"
            )
        )
}