package uz.javokhirdev.randoms.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import uz.javokhirdev.randoms.data.network.response.FactResponse
import uz.javokhirdev.randoms.data.network.response.ImageResponse
import uz.javokhirdev.randoms.data.network.response.JokeListResponse
import uz.javokhirdev.randoms.data.network.response.JokeResponse

interface RandomsApi {

    @GET
    suspend fun getRandomImage(@Url baseUrl: String): Response<ImageResponse>

    @GET
    suspend fun getJoke(@Url baseUrl: String): Response<JokeResponse>

    @GET
    suspend fun getJokeList(@Url baseUrl: String): Response<JokeListResponse>

    @GET
    suspend fun getFact(@Url baseUrl: String): Response<FactResponse>
}