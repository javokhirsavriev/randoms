package uz.javokhirdev.randoms.data.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.javokhirdev.randoms.data.base.DataMapper
import uz.javokhirdev.randoms.data.model.RandomModel

@Keep
data class RandomResponse(
    // Image
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("image_link")
    val imageLink: String? = null,
    @SerializedName("file")
    val file: String? = null,
    @SerializedName("image")
    val image: String? = null,

    // Joke
    @SerializedName("joke")
    val joke: String? = null,
    @SerializedName("value")
    val value: String? = null,
    @SerializedName("setup")
    val setup: String? = null,
    @SerializedName("delivery")
    val delivery: String? = null,

    // Fact
    @SerializedName("facts")
    val facts: List<String>? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("data")
    val factData: Random? = null,

    // Quote
    @SerializedName("quote")
    val quote: String? = null,
    @SerializedName("quoteText")
    val quoteText: String? = null,
    @SerializedName("quoteAuthor")
    val quoteAuthor: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("sentence")
    val sentence: String? = null,
    @SerializedName("en")
    val en: String? = null,

    // More
    @SerializedName("activity")
    val activity: String? = null,
    @SerializedName("slip")
    val slip: Random? = null
) : DataMapper<RandomResponse, RandomModel> {

    @Keep
    data class Random(
        @SerializedName("fact")
        val fact: String? = null,
        @SerializedName("advice")
        val advice: String? = null
    )

    override fun RandomResponse.mapToDomain(): RandomModel {
        return RandomModel(
            title = getJokeTitle() ?: getFactTitle() ?: getQuoteTitle() ?: getMoreTitle(),
            subtitle = delivery ?: getQuoteSubtitle(),
            imageUrl = url ?: imageLink ?: file ?: image
        )
    }

    private fun getJokeTitle(): String? {
        return joke ?: value ?: setup
    }

    private fun getFactTitle(): String? {
        return facts?.firstOrNull() ?: text ?: factData?.fact ?: value
    }

    private fun getQuoteTitle(): String? {
        return quote ?: quoteText ?: sentence ?: en
    }

    private fun getQuoteSubtitle(): String? {
        return quoteAuthor ?: author
    }

    private fun getMoreTitle(): String? {
        return activity ?: slip?.advice
    }
}