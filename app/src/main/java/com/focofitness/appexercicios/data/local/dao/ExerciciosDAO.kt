package com.focofitness.appexercicios.data.local.dao

import androidx.room.*
import com.focofitness.appexercicios.domain.model.Exercicios
import com.focofitness.appexercicios.domain.model.RotinaDiaria
import com.focofitness.appexercicios.domain.model.Usuario
import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciciosDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExercicio(exercicios: Exercicios)

    @Query("SELECT * FROM lista_exercicios")
    fun buscarTodosExercicios(): Flow<List<Exercicios>>

    @Delete
    suspend fun deletarExercicio(exercicios: Exercicios)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRotina(rotinaDiaria: RotinaDiaria)

    @Query("SELECT * FROM rotina_database")
    fun buscarRotinas(): Flow<List<RotinaDiaria>>

    @Delete
    suspend fun deletarRotina(rotinaDiaria: RotinaDiaria)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuario_table WHERE id = :id1")
    fun buscarUsuario(id1: Int): Usuario

}