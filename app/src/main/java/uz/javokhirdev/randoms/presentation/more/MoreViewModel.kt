package uz.javokhirdev.randoms.presentation.more

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
class MoreViewModel @Inject constructor() : ViewModel() {

    private val uiStateData = MutableStateFlow(emptyList<ListModel>())
    val uiState = uiStateData.asStateFlow()

    init {
        getMoreData()
    }

    private fun getMoreData() {
        viewModelScope.launch {
            uiStateData.update { moreItems }
        }
    }

    private val moreItems
        get() = listOf(
            ListModel(
                id = 1,
                title = "Activities"
            ),
            ListModel(
                id = 2,
                title = "Excuses for various situations"
            ),
            ListModel(
                id = 3,
                title = "Advice slips"
            )
        )
}