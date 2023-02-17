package com.example.appexercicios.data.local

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appexercicios.data.local.dao.ExerciciosDAO
import com.example.appexercicios.domain.model.Exercicios
import com.example.appexercicios.domain.model.RotinaDiaria
import com.example.appexercicios.domain.model.Usuario
import com.example.appexercicios.feature_medidas.data.data_source.MedidasDAO
import com.example.appexercicios.feature_medidas.domain.model.Medidas

@Database(
    entities = [
        Exercicios::class,
        RotinaDiaria::class,
        Usuario::class,
        Medidas::class
               ],
    version = 1,
    )
@TypeConverters(DatabaseConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): AppDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            } else {
                Room.databaseBuilder(context, AppDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun exerciciosDao(): ExerciciosDAO
    abstract fun medidasDao(): MedidasDAO

}