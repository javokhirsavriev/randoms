package uz.javokhirdev.randoms.app.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.javokhirdev.randoms.core.Constants
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton]
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().apply {
        connectTimeout(120, TimeUnit.SECONDS)
        readTimeout(120, TimeUnit.SECONDS)
        writeTimeout(120, TimeUnit.SECONDS)
        addInterceptor {
            val requestBuilder = it.request().newBuilder()
            requestBuilder.addHeader("Accept", "application/json")
            it.proceed(requestBuilder.build())
        }
    }.build()

    @[Provides Singleton]
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val nullOnEmptyConverterFactory = object : Converter.Factory() {
            fun converterFactory() = this
            override fun responseBodyConverter(
                type: Type,
                annotations: Array<out Annotation>,
                retrofit: Retrofit
            ) = object : Converter<ResponseBody, Any?> {
                val converter = retrofit.nextResponseBodyConverter<Any?>(
                    converterFactory(), type, annotations
                )

                override fun convert(value: ResponseBody) =
                    if (value.contentLength() != 0L) converter.convert(value) else null
            }
        }

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

//    @[Provides Singleton]
//    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
//        val chuckerCollector = ChuckerCollector(
//            context = context,
//            showNotification = true,
//            retentionPeriod = RetentionManager.Period.ONE_HOUR
//        )
//
//        return ChuckerInterceptor.Builder(context)
//            .collector(chuckerCollector)
//            .maxContentLength(250_000L)
//            .alwaysReadResponseBody(true)
//            .build()
//    }
}
