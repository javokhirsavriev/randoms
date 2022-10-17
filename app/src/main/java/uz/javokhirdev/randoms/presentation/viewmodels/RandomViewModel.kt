package uz.javokhirdev.randoms.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.RandomModel
import uz.javokhirdev.randoms.domain.RandomUseCases
import uz.javokhirdev.randoms.presentation.navigation.NavArguments
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
    private val randomUseCases: RandomUseCases
) : ViewModel() {

    private val randomData = MutableLiveData<UIState<RandomModel>>()
    val random: LiveData<UIState<RandomModel>> = randomData

    init {
        getRandom()
    }

    fun getRandom() {
        randomData.value = UIState.Loading

        val model = NavArguments.model ?: return

        viewModelScope.launch {
            if (model.isImageUrl) {
                randomData.value = UIState.success(RandomModel(imageUrl = model.baseUrl))
            } else if (model.isListUrl) {
                randomUseCases.getRandomList(model.baseUrl.orEmpty())
            } else if (model.isStringListUrl) {
                randomUseCases.getRandomStringList(model.baseUrl.orEmpty())
            } else {
                randomUseCases.getRandom(model.baseUrl.orEmpty())
            }.collectLatest {
                randomData.value = it
            }
        }
    }
}