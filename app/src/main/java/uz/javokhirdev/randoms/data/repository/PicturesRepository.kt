package uz.javokhirdev.randoms.data.repository

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.base.BaseRepository
import uz.javokhirdev.randoms.data.network.RandomsApi
import javax.inject.Inject

interface PicturesRepository {

    suspend fun getRandomImage(baseUrl: String): Flow<UIState<String>>
}

class PicturesRepositoryImpl @Inject constructor(
    private val randomsApi: RandomsApi
) : BaseRepository(), PicturesRepository {

    override suspend fun getRandomImage(baseUrl:String): Flow<UIState<String>> {
        return doNetworkRequest { randomsApi.getRandomImage(baseUrl) }
    }
}
