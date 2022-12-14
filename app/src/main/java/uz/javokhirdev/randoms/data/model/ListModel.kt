package uz.javokhirdev.randoms.data.model

data class ListModel(
    val id: Int? = null,
    val title: String? = null,
    val cover: String? = null,
    val baseUrl: String? = null,
    val isImageUrl: Boolean = false,
    val isListUrl: Boolean = false,
    val isStringListUrl: Boolean = false,
    val isProgramming: Boolean = false,
    val type: RandomType = RandomType.MORE
)
