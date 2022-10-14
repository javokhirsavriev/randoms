package uz.javokhirdev.randoms.domain

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.JokeModel

fun interface GetJoke : suspend (String) -> Flow<UIState<JokeModel>>

fun interface GetJokeList : suspend (String) -> Flow<UIState<JokeModel>>

data class JokesUseCases(
    val getJoke: GetJoke,
    val getJokeList: GetJokeList,
)
