package uz.javokhirdev.randoms.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.javokhirdev.randoms.data.repository.PicturesRepository
import uz.javokhirdev.randoms.domain.PicturesUseCases

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @[Provides ViewModelScoped]
    fun providesPicturesUseCases(repository: PicturesRepository): PicturesUseCases {
        return PicturesUseCases(
            getRandomImage = repository::getRandomImage
        )
    }
}
