package com.example.appexercicios.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.appexercicios.data.local.AppDatabase
import com.example.appexercicios.domain.model.Exercicios
import com.example.appexercicios.domain.model.RotinaDiaria
import com.example.appexercicios.domain.model.Usuario
import com.example.appexercicios.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(appDatabase: AppDatabase): LocalDataSource {

    private val daoExerc = appDatabase.exerciciosDao()

    override fun buscarTodosExercicios(): Flow<List<Exercicios>> {
        return this.daoExerc.buscarTodosExercicios()
    }
    override suspend fun addExercicios(exercicios: Exercicios) {
        this.daoExerc.addExercicio(exercicios)
    }
    override suspend fun deletarExercicios(exercicios: Exercicios) {
        this.daoExerc.deletarExercicio(exercicios)
    }

    override fun buscarRotinas(): Flow<List<RotinaDiaria>> {
        return this.daoExerc.buscarRotinas()
    }

    override suspend fun addRotina(rotinaDiaria: RotinaDiaria) {
        this.daoExerc.addRotina(rotinaDiaria)
    }

    override suspend fun deletarRotina(rotinaDiaria: RotinaDiaria) {
        this.daoExerc.deletarRotina(rotinaDiaria)
    }

    override suspend fun addUsuario(usuario: Usuario) {
        try {
            this.daoExerc.addUsuario(usuario)
        }catch (e: Exception){
            Log.e(TAG , "atualizarPerfilLocalData: $e" , )
        }
    }
    override suspend fun buscarUsuario(id: Int): Usuario {
        return this.daoExerc.buscarUsuario(id)
    }


}