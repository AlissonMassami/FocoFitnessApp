package com.focofitness.appexercicios.data.local

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.focofitness.appexercicios.data.local.dao.ExerciciosDAO
import com.focofitness.appexercicios.domain.model.Exercicios
import com.focofitness.appexercicios.domain.model.RotinaDiaria
import com.focofitness.appexercicios.domain.model.Usuario
import com.focofitness.appexercicios.feature_dieta.data.local.TabelaDAO
import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.ListaAlimentos
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_medidas.data.data_source.MedidasDAO
import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas

@Database(
    entities = [
        Exercicios::class,
        RotinaDiaria::class,
        Usuario::class,
        Medidas::class,
        TabelaCalorica::class,
        CaloriasDiarias::class
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
    abstract fun tabelaDao(): TabelaDAO


}