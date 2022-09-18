package uz.javokhirdev.randoms.data.base

interface DataMapper<T, S> {
    fun T.mapToDomain(): S
}

fun <T : DataMapper<T, S>, S> T.mapToDomain(): S = this.mapToDomain()
