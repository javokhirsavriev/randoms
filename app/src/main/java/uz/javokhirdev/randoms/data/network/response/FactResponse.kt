package uz.javokhirdev.randoms.data.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.javokhirdev.randoms.data.base.DataMapper

@Keep
data class FactResponse(
    @SerializedName("facts")
    val facts: List<String>? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("data")
    val factData: FactData? = null,
    @SerializedName("value")
    val value: String? = null
) : DataMapper<FactResponse, String> {

    @Keep
    data class FactData(
        @SerializedName("fact")
        val fact: String? = null
    )

    override fun FactResponse.mapToDomain(): String {
        return facts?.firstOrNull() ?: text ?: factData?.fact ?: value ?: ""
    }
}