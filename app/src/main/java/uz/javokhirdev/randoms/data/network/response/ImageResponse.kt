package uz.javokhirdev.randoms.data.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.javokhirdev.randoms.data.base.DataMapper

@Keep
data class ImageResponse(
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("image_link")
    val imageLink: String? = null,
    @SerializedName("file")
    val file: String? = null,
    @SerializedName("image")
    val image: String? = null
) : DataMapper<ImageResponse, String> {

    override fun ImageResponse.mapToDomain(): String {
        return url ?: imageLink ?: file ?: image ?: ""
    }
}
