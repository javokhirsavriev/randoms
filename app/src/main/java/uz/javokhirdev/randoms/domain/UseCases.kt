package uz.javokhirdev.randoms.domain

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.JokeModel

fun interface GetRandomImage : suspend (String) -> Flow<UIState<String>>

fun interface GetJoke : suspend (String) -> Flow<UIState<JokeModel>>

fun interface GetJokeList : suspend (String) -> Flow<UIState<JokeModel>>

fun interface GetFact : suspend (String) -> Flow<UIState<String>>

data class UseCases(
    val getRandomImage: GetRandomImage,
    val getJoke: GetJoke,
    val getJokeList: GetJokeList,
    val getFact: GetFact
)
