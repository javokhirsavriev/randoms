package uz.javokhirdev.randoms.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.javokhirdev.randoms.data.repository.RandomRepository
import uz.javokhirdev.randoms.domain.RandomUseCases

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @[Provides ViewModelScoped]
    fun providesRandomUseCases(repository: RandomRepository): RandomUseCases {
        return RandomUseCases(
            getRandom = repository::getRandom,
            getRandomList = repository::getRandomList,
            getRandomStringList = repository::getRandomStringList,
        )
    }
}
