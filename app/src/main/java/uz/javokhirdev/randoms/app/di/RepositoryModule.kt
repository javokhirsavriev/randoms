package uz.javokhirdev.randoms.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.javokhirdev.randoms.data.repository.RandomRepository
import uz.javokhirdev.randoms.data.repository.RandomRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds]
    fun bindRandomRepository(impl: RandomRepositoryImpl): RandomRepository
}
