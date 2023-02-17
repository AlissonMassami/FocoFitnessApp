package com.example.appexercicios.feature_paging.presentation.paging_screen

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appexercicios.domain.model.Exercicios
import com.example.appexercicios.domain.model.Usuario
import com.example.appexercicios.domain.repository.LocalDataSource
import com.example.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCases: UseCases,
    private val localDataSource: LocalDataSource,

): ViewModel() {

    val lista = listOf<Exercicios>(
        Exercicios(exercicio = "Supino Reto",
        tipo = "Peito",
        series = 3,
        repeticoes = 12,),
        Exercicios(exercicio = "Supino Inclinado",
            tipo = "Peito",
            series = 3,
            repeticoes = 12,),
        Exercicios(exercicio = "Crucifixo Reto",
            tipo = "Peito",
            series = 3,
            repeticoes = 12,),
        Exercicios(exercicio = "Agachamento",
            tipo = "Pernas",
            series = 3,
            repeticoes = 12,),
        Exercicios(exercicio = "Flexão",
            tipo = "Peito",
            series = 3,
            repeticoes = 12,),
        Exercicios(exercicio = "Abdominal Supra",
            tipo = "Abdomen",
            series = 3,
            repeticoes = 12,),
        Exercicios(exercicio = "Elevação Lateral com Halteres",
            tipo = "Ombro ",
            series = 3,
            repeticoes = 12,),
    )
    val listaExercicios = localDataSource.buscarTodosExercicios()
    init {
        viewModelScope.launch {
            lista.forEach { exerc->
                localDataSource.addExercicios(exerc)
            }
        }
    }
    fun atualizarPerfil(usuario: Usuario){

        try {
            viewModelScope.launch {
                localDataSource.addUsuario(usuario)
            }

        }catch (e: Exception){
            Log.e(ContentValues.TAG , "atualizarPerfilViewModel: $e" , )
        }
    }

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnBoardingUseCase(completed = completed)
        }
    }

}

