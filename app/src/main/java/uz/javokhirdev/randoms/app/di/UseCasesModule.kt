package uz.javokhirdev.randoms.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.javokhirdev.randoms.data.repository.RandomRepository
import uz.javokhirdev.randoms.domain.UseCases

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @[Provides ViewModelScoped]
    fun providesUseCases(repository: RandomRepository): UseCases {
        return UseCases(
            getRandomImage = repository::getRandomImage,
            getJoke = repository::getJoke,
            getJokeList = repository::getJokeList,
            getFact = repository::getFact
        )
    }
}
