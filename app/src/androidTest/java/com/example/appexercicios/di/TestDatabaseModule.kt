package com.example.appexercicios.di

import android.content.Context
import androidx.room.Room
import com.example.appexercicios.data.local.AppDatabase
import com.example.appexercicios.data.repository.LocalDataSourceImpl
import com.example.appexercicios.domain.repository.LocalDataSource
import com.example.appexercicios.feature_medidas.data.repository.MedidasRepositoryImpl
import com.example.appexercicios.feature_medidas.domain.repository.MedidasRepository
import com.example.appexercicios.feature_medidas.domain.use_case.*
import com.example.appexercicios.util.Constants.EXERCICIOS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
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
    ): MedidasUseCases{
        return MedidasUseCases(
            getNota = GetNota(repository),
            getTodasNotas = GetTodasNotas(repository) ,
            inserirNota = InserirNota(repository),
            deleteNota = DeleteNota(repository)
        )
    }

}