package com.example.appexercicios.presentation.PagExercicios

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appexercicios.domain.model.Exercicios
import com.example.appexercicios.domain.repository.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciciosViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
): ViewModel(){

    val listaExercicios = localDataSource.buscarTodosExercicios()

    fun addNovoExercicio(exercicios: Exercicios){
        viewModelScope.launch {
            localDataSource.addExercicios(exercicios)
        }
    }
    fun deletarExercicio(exercicios: Exercicios){
        viewModelScope.launch {
           try {
               localDataSource.deletarExercicios(exercicios)
           }catch (e: Exception){
               Log.e("tag", "deletarExercicio: $e", )
           }
        }
    }

}