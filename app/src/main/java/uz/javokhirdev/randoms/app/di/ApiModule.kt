package uz.javokhirdev.randoms.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.javokhirdev.randoms.data.network.RandomsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides Singleton]
    fun provideRandomsApi(retrofit: Retrofit): RandomsApi = retrofit.create(RandomsApi::class.java)
}
