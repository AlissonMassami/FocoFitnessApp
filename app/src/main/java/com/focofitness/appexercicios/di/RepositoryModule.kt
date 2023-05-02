package com.focofitness.appexercicios.di

import android.content.Context
import com.example.borutoapp.domain.use_cases.UseCases
import com.example.borutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.focofitness.appexercicios.feature_paging.data.repository.DataStoreOperationsImpl
import com.focofitness.appexercicios.feature_paging.data.repository.RepositoryPaging
import com.focofitness.appexercicios.feature_paging.domain.repository.DataStoreOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: RepositoryPaging): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository)
        )
    }

}