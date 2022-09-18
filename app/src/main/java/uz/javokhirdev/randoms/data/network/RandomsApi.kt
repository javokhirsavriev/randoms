package uz.javokhirdev.randoms.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import uz.javokhirdev.randoms.data.network.response.ImageResponse

interface RandomsApi {

    @GET
    suspend fun getRandomImage(@Url baseUrl: String): Response<ImageResponse>
}