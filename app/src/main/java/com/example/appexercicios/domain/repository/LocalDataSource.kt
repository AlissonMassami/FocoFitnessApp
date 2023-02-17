package com.example.appexercicios.domain.repository

import com.example.appexercicios.domain.model.Exercicios
import com.example.appexercicios.domain.model.RotinaDiaria
import com.example.appexercicios.domain.model.Usuario
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    fun buscarTodosExercicios(): Flow<List<Exercicios>>
    suspend fun addExercicios(exercicios: Exercicios)
    suspend fun deletarExercicios(exercicios: Exercicios)

    fun buscarRotinas(): Flow<List<RotinaDiaria>>
    suspend fun addRotina(rotinaDiaria: RotinaDiaria)
    suspend fun deletarRotina(rotinaDiaria: RotinaDiaria)

    suspend fun addUsuario(usuario: Usuario)
    suspend fun buscarUsuario(id: Int): Usuario
}