package uz.javokhirdev.randoms.data.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.javokhirdev.randoms.data.base.DataMapper
import uz.javokhirdev.randoms.data.model.JokeModel

@Keep
data class JokeResponse(
    @SerializedName("joke")
    val joke: String? = null,
    @SerializedName("value")
    val value: String? = null,
    @SerializedName("setup")
    val setup: String? = null,
    @SerializedName("delivery")
    val delivery: String? = null
) : DataMapper<JokeResponse, JokeModel> {

    override fun JokeResponse.mapToDomain(): JokeModel {
        return JokeModel(
            question = joke ?: value ?: setup,
            punchline = delivery
        )
    }
}

class JokeListResponse : ArrayList<JokeListResponse.Joke>(),
    DataMapper<JokeListResponse, JokeModel> {

    @Keep
    data class Joke(
        @SerializedName("punchline")
        val punchline: String? = null,
        @SerializedName("question")
        val question: String? = null
    )

    override fun JokeListResponse.mapToDomain(): JokeModel {
        val joke = firstOrNull()
        return JokeModel(
            question = joke?.question,
            punchline = joke?.punchline
        )
    }
}