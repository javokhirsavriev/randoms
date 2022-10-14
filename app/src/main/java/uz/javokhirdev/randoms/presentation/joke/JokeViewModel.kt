package uz.javokhirdev.randoms.presentation.joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.JokeModel
import uz.javokhirdev.randoms.domain.JokesUseCases
import uz.javokhirdev.randoms.presentation.navigation.NavArguments
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val useCases: JokesUseCases
) : ViewModel() {

    private val uiStateData = MutableStateFlow<UIState<JokeModel>>(UIState.Idle)
    val uiState = uiStateData.asStateFlow()

    init {
        getJoke()
    }

    fun getJoke() {
        val model = NavArguments.listModel ?: return

        viewModelScope.launch {
            if (model.isJokeListUrl) {
                useCases.getJokeList(model.baseUrl.orEmpty())
            } else {
                useCases.getJoke(model.baseUrl.orEmpty())
            }.collectLatest { uiStateData.value = it }
        }
    }
}