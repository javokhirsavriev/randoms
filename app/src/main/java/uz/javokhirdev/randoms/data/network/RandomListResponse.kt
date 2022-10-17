package uz.javokhirdev.randoms.data.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.javokhirdev.randoms.data.base.DataMapper
import uz.javokhirdev.randoms.data.model.RandomModel

@Keep
data class RandomListResponse(
    @SerializedName("punchline")
    val punchline: String? = null,
    @SerializedName("question")
    val question: String? = null,
    @SerializedName("excuse")
    val excuse: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("quote")
    val quote: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("character")
    val character: String? = null,
    @SerializedName("q")
    val q: String? = null,
    @SerializedName("a")
    val a: String? = null
) : DataMapper<RandomListResponse, RandomModel> {

    override fun RandomListResponse.mapToDomain(): RandomModel {
        return RandomModel(
            title = question ?: excuse ?: quote ?: q,
            subtitle = punchline ?: category ?: author ?: character ?: a
        )
    }
}