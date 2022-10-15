package uz.javokhirdev.randoms.presentation.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.JokeModel
import uz.javokhirdev.randoms.data.model.ListModel
import uz.javokhirdev.randoms.domain.UseCases
import uz.javokhirdev.randoms.presentation.navigation.NavArguments
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val uiStateData = MutableStateFlow(emptyList<ListModel>())
    val uiState = uiStateData.asStateFlow()

    private val jokeData = MutableStateFlow<UIState<JokeModel>>(UIState.Idle)
    val joke = jokeData.asStateFlow()

    fun getJokes() {
        viewModelScope.launch { uiStateData.value = jokes }
    }

    fun getJoke() {
        val model = NavArguments.model ?: return

        viewModelScope.launch {
            if (model.isListUrl) {
                useCases.getJokeList(model.baseUrl.orEmpty())
            } else {
                useCases.getJoke(model.baseUrl.orEmpty())
            }.collectLatest {
                jokeData.value = it
            }
        }
    }

    private val jokes
        get() = listOf(
            ListModel(
                id = 1,
                title = "Dad jokes",
                cover = "file:///android_asset/jokes/image_01.webp",
                baseUrl = "https://icanhazdadjoke.com/"
            ),
            ListModel(
                id = 2,
                title = "Chuck Norris jokes",
                cover = "file:///android_asset/jokes/image_02.webp",
                baseUrl = "https://api.chucknorris.io/jokes/random"
            ),
            ListModel(
                id = 3,
                title = "Yo Momma jokes",
                cover = "file:///android_asset/jokes/image_03.webp",
                baseUrl = "https://yomomma-api.herokuapp.com/jokes"
            ),
            ListModel(
                id = 4,
                title = "Programming jokes",
                cover = "file:///android_asset/jokes/image_04.webp",
                baseUrl = "https://v2.jokeapi.dev/joke/Any"
            ),
            ListModel(
                id = 5,
                title = "Dev jokes",
                cover = "file:///android_asset/jokes/image_05.webp",
                baseUrl = "https://backend-omega-seven.vercel.app/api/getjoke",
                isListUrl = true
            )
        )
}