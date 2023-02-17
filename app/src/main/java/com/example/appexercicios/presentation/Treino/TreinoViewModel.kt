package com.example.appexercicios.presentation.Treino

import androidx.lifecycle.ViewModel
import com.example.appexercicios.domain.repository.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TreinoViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
): ViewModel(){
    val listaRotinaDiaria = localDataSource.buscarRotinas()


}