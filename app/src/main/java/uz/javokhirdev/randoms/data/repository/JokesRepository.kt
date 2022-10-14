package uz.javokhirdev.randoms.data.repository

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.base.BaseRepository
import uz.javokhirdev.randoms.data.model.JokeModel
import uz.javokhirdev.randoms.data.network.RandomsApi
import javax.inject.Inject

interface JokesRepository {

    suspend fun getJoke(baseUrl: String): Flow<UIState<JokeModel>>

    suspend fun getJokeList(baseUrl: String): Flow<UIState<JokeModel>>
}

class JokesRepositoryImpl @Inject constructor(
    private val randomsApi: RandomsApi
) : BaseRepository(), JokesRepository {

    override suspend fun getJoke(baseUrl: String): Flow<UIState<JokeModel>> {
        return doNetworkRequest { randomsApi.getJoke(baseUrl) }
    }

    override suspend fun getJokeList(baseUrl: String): Flow<UIState<JokeModel>> {
        return doNetworkRequest { randomsApi.getJokeList(baseUrl) }
    }
}
