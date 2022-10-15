package uz.javokhirdev.randoms.data.repository

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.base.BaseRepository
import uz.javokhirdev.randoms.data.model.JokeModel
import uz.javokhirdev.randoms.data.network.RandomsApi
import javax.inject.Inject

interface RandomRepository {

    suspend fun getRandomImage(baseUrl: String): Flow<UIState<String>>

    suspend fun getJoke(baseUrl: String): Flow<UIState<JokeModel>>

    suspend fun getJokeList(baseUrl: String): Flow<UIState<JokeModel>>

    suspend fun getFact(baseUrl: String): Flow<UIState<String>>
}

class RandomRepositoryImpl @Inject constructor(
    private val randomsApi: RandomsApi
) : BaseRepository(), RandomRepository {

    override suspend fun getRandomImage(baseUrl: String): Flow<UIState<String>> {
        return doNetworkRequest { randomsApi.getRandomImage(baseUrl) }
    }

    override suspend fun getJoke(baseUrl: String): Flow<UIState<JokeModel>> {
        return doNetworkRequest { randomsApi.getJoke(baseUrl) }
    }

    override suspend fun getJokeList(baseUrl: String): Flow<UIState<JokeModel>> {
        return doNetworkRequest { randomsApi.getJokeList(baseUrl) }
    }

    override suspend fun getFact(baseUrl: String): Flow<UIState<String>> {
        return doNetworkRequest { randomsApi.getFact(baseUrl) }
    }
}
