package uz.javokhirdev.randoms.data.repository

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.base.BaseRepository
import uz.javokhirdev.randoms.data.model.RandomModel
import uz.javokhirdev.randoms.data.network.RandomsApi
import javax.inject.Inject

interface RandomRepository {

    suspend fun getRandom(baseUrl: String): Flow<UIState<RandomModel>>

    suspend fun getRandomList(baseUrl: String): Flow<UIState<RandomModel>>

    suspend fun getRandomStringList(baseUrl: String): Flow<UIState<RandomModel>>
}

class RandomRepositoryImpl @Inject constructor(
    private val randomsApi: RandomsApi
) : BaseRepository(), RandomRepository {

    override suspend fun getRandom(baseUrl: String): Flow<UIState<RandomModel>> {
        return doNetworkRequest { randomsApi.getRandom(baseUrl) }
    }

    override suspend fun getRandomList(baseUrl: String): Flow<UIState<RandomModel>> {
        return doNetworkRequestList { randomsApi.getRandomList(baseUrl) }
    }

    override suspend fun getRandomStringList(baseUrl: String): Flow<UIState<RandomModel>> {
        return doNetworkRequestStringList { randomsApi.getRandomStringList(baseUrl) }
    }
}
