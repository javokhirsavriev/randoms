package uz.javokhirdev.randoms.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RandomsApi {

    @GET
    suspend fun getRandom(@Url baseUrl: String): Response<RandomResponse>

    @GET
    suspend fun getRandomList(@Url baseUrl: String): Response<List<RandomListResponse>>

    @GET
    suspend fun getRandomStringList(@Url baseUrl: String): Response<List<String>>
}