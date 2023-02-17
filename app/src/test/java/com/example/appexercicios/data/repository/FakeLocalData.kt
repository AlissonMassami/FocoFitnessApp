package com.example.appexercicios.data.repository

import com.example.appexercicios.domain.model.Exercicios
import com.example.appexercicios.domain.model.RotinaDiaria
import com.example.appexercicios.domain.model.Usuario
import com.example.appexercicios.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalData : LocalDataSource {

    private val exerciciosList = mutableListOf<Exercicios>()
    private val rotinasList = mutableListOf<RotinaDiaria>()
    private val userList = mutableListOf<Usuario>()


    override fun buscarTodosExercicios(): Flow<List<Exercicios>> {
        return flow { emit(exerciciosList) }
    }

    override suspend fun addExercicios(exercicios: Exercicios) {
        exerciciosList.add(exercicios)
    }

    override suspend fun deletarExercicios(exercicios: Exercicios) {
        exerciciosList.remove(exercicios)
    }

    override fun buscarRotinas(): Flow<List<RotinaDiaria>> {
        return flow { emit(rotinasList) }
    }

    override suspend fun addRotina(rotinaDiaria: RotinaDiaria) {
        rotinasList.add(rotinaDiaria)
    }

    override suspend fun deletarRotina(rotinaDiaria: RotinaDiaria) {
        rotinasList.remove(rotinaDiaria)
    }

    override suspend fun addUsuario(usuario: Usuario) {
        userList.add(usuario)
    }

    override suspend fun buscarUsuario(id: Int): Usuario {
        return userList[id]
    }
}