package uz.javokhirdev.randoms.domain

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.RandomModel

fun interface GetRandom : suspend (String) -> Flow<UIState<RandomModel>>

fun interface GetRandomList : suspend (String) -> Flow<UIState<RandomModel>>

fun interface GetRandomStringList : suspend (String) -> Flow<UIState<RandomModel>>

data class RandomUseCases(
    val getRandom: GetRandom,
    val getRandomList: GetRandomList,
    val getRandomStringList: GetRandomStringList
)
