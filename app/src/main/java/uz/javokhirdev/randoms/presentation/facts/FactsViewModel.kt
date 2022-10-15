package uz.javokhirdev.randoms.presentation.facts

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
class FactsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val uiStateData = MutableStateFlow(emptyList<ListModel>())
    val uiState = uiStateData.asStateFlow()

    private val factData = MutableStateFlow<UIState<String>>(UIState.Idle)
    val fact = factData.asStateFlow()

    fun getFacts() {
        viewModelScope.launch { uiStateData.value = facts }
    }

    fun getFact() {
        val model = NavArguments.model ?: return

        viewModelScope.launch {
            useCases.getFact(model.baseUrl.orEmpty()).collectLatest {
                factData.value = it
            }
        }
    }

    private val facts
        get() = listOf(
            ListModel(
                id = 1,
                title = "Facts of cats",
                cover = "file:///android_asset/facts/image_01.webp",
                baseUrl = "https://cat-fact.herokuapp.com/facts/random"
            ),
            ListModel(
                id = 2,
                title = "Facts of dogs",
                cover = "file:///android_asset/facts/image_02.webp",
                baseUrl = "https://dog-api.kinduff.com/api/facts?number=1",
                isListUrl = true
            ),
//            ListModel(
//                id = 3,
//                title = "Anime facts",
//                cover = "file:///android_asset/facts/image_03.webp"
//            ),
            ListModel(
                id = 4,
                title = "Useless, but true facts",
                cover = "file:///android_asset/facts/image_04.webp",
                baseUrl = "https://uselessfacts.jsph.pl/random.json?language=en"
            ),
            ListModel(
                id = 5,
                title = "Fun facts",
                cover = "file:///android_asset/facts/image_05.webp",
                baseUrl = "https://asli-fun-fact-api.herokuapp.com/"
            ),
            ListModel(
                id = 6,
                title = "Facts about numbers",
                cover = "file:///android_asset/facts/image_06.webp",
                baseUrl = "http://numbersapi.com/random/${numberType}?json"
            ),
            ListModel(
                id = 7,
                title = "Chuck Norris facts",
                cover = "file:///android_asset/facts/image_07.webp",
                baseUrl = "https://api.chucknorris.io/jokes/random"
            )
//            ListModel(
//                id = 8,
//                title = "Knowledge facts",
//                cover = "file:///android_asset/facts/image_08.webp"
//            ),
//            ListModel(
//                id = 9,
//                title = "Random facts",
//                cover = "file:///android_asset/facts/image_09.webp"
//            )
        )

    private val numberType get() = listOf("trivia", "math", "date", "year").random()
}