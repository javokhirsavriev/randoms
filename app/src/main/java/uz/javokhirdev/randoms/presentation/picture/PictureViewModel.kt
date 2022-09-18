package uz.javokhirdev.randoms.presentation.picture

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
import uz.javokhirdev.randoms.domain.PicturesUseCases
import uz.javokhirdev.randoms.presentation.navigation.NavArguments
import javax.inject.Inject

@HiltViewModel
class PictureViewModel @Inject constructor(
    private val useCases: PicturesUseCases
) : ViewModel() {

    private val uiStateData = MutableLiveData<UIState<String>>()
    val uiState: LiveData<UIState<String>> = uiStateData

    init {
        getRandomImage()
    }

    fun getRandomImage() {
        val model = NavArguments.listModel ?: return

        viewModelScope.launch {
            if (model.isImageUrl) {
                uiStateData.value = UIState.success(model.baseUrl)
            } else {
                useCases.getRandomImage(model.baseUrl.orEmpty())
                    .collectLatest { uiStateData.value = it }
            }
        }
    }
}