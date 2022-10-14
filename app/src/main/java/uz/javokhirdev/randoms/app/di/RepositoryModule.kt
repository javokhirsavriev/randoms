package uz.javokhirdev.randoms.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.javokhirdev.randoms.data.repository.JokesRepository
import uz.javokhirdev.randoms.data.repository.JokesRepositoryImpl
import uz.javokhirdev.randoms.data.repository.PicturesRepository
import uz.javokhirdev.randoms.data.repository.PicturesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds]
    fun bindPicturesRepository(impl: PicturesRepositoryImpl): PicturesRepository

    fun bindJokesRepository(impl: JokesRepositoryImpl): JokesRepository
}
