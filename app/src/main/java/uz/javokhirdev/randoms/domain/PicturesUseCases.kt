package uz.javokhirdev.randoms.domain

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.randoms.data.UIState

fun interface GetRandomImage : suspend (String) -> Flow<UIState<String>>

data class PicturesUseCases(
    val getRandomImage: GetRandomImage
)
