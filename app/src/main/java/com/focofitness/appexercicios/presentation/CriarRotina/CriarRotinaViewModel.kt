package com.focofitness.appexercicios.presentation.CriarRotina

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.focofitness.appexercicios.domain.model.Exercicios
import com.focofitness.appexercicios.domain.model.RotinaDiaria
import com.focofitness.appexercicios.domain.repository.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CriarRotinaViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
): ViewModel(){

    val listaExercicios = localDataSource.buscarTodosExercicios()
    val listaRotinaDiaria = localDataSource.buscarRotinas()

    val itemlist = mutableStateListOf<Exercicios>()

    fun addExercicioRotina(exercicios: Exercicios){
        itemlist.add(exercicios)
    }
    fun removerExercicioRotina(exercicios: Exercicios){
        itemlist.remove(exercicios)
    }

    fun salvarRotinaDiaria(rotinaDiaria: RotinaDiaria){
        viewModelScope.launch {
            localDataSource.addRotina(rotinaDiaria)
        }

    }

    fun addNovoExercicio(exercicios: Exercicios){
        viewModelScope.launch {
            localDataSource.addExercicios(exercicios)
        }
    }
    fun deletarExercicio(exercicios: Exercicios){
        viewModelScope.launch {
            localDataSource.deletarExercicios(exercicios)
        }
    }
    fun addNovaRotina(rotinaDiaria: RotinaDiaria){
        viewModelScope.launch {
            localDataSource.addRotina(rotinaDiaria)
        }
    }
    fun deletarNovaRotina(rotinaDiaria: RotinaDiaria){
        viewModelScope.launch {
            localDataSource.addRotina(rotinaDiaria)
        }
    }





}