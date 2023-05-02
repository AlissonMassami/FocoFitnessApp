package com.focofitness.appexercicios.di

import android.content.Context
import androidx.room.Room
import com.focofitness.appexercicios.data.local.AppDatabase
import com.focofitness.appexercicios.data.repository.LocalDataSourceImpl
import com.focofitness.appexercicios.domain.repository.LocalDataSource
import com.focofitness.appexercicios.feature_dieta.data.repository.DietaRepositoryImpl
import com.focofitness.appexercicios.feature_dieta.domain.repository.DietaRepository
import com.focofitness.appexercicios.feature_dieta.use_cases.*
import com.focofitness.appexercicios.feature_medidas.data.repository.MedidasRepositoryImpl
import com.focofitness.appexercicios.feature_medidas.domain.repository.MedidasRepository
import com.focofitness.appexercicios.feature_medidas.domain.use_case.*
import com.focofitness.appexercicios.util.Constants.EXERCICIOS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            EXERCICIOS_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: AppDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            appDatabase = database
        )
    }

    @Provides
    @Singleton
    fun provideRepository(
        database: AppDatabase
    ): MedidasRepository {
        return MedidasRepositoryImpl(
            database.medidasDao()
        )
    }
    @Provides
    @Singleton
    fun provideUseCases(
        repository: MedidasRepository
    ): MedidasUseCases {
        return MedidasUseCases(
            getNota = GetNota(repository) ,
            getTodasNotas = GetTodasNotas(repository) ,
            inserirNota = InserirNota(repository) ,
            deleteNota = DeleteNota(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDietaRepository(
        database: AppDatabase
    ): DietaRepository {
        return DietaRepositoryImpl(
            database.tabelaDao()
        )
    }
    @Provides
    @Singleton
    fun provideTabelaUseCases(
        repository: DietaRepository
    ): DietaUseCases {
        return DietaUseCases(
            getAlimentoSelecionadoUseCase = GetAlimentoSelecionadoUseCase(repository) ,
            searchAlimentosUseCase = SearchAlimentosUseCase(repository) ,
            getTodosAlimentosUseCase = GetTodosAlimentosUseCase(repository),
            searchDiaSemanaUseCase = SearchDiaSemanaUseCase(repository),
            inserirConsumoDiarioUseCase = InserirConsumoDiarioUseCase(repository),
            deleteConsumoDiarioUseCase = DeleteConsumoDiarioUseCase(repository),
            inserirAlimentoUseCase = InserirAlimentoUseCase(repository)
        )
    }


}