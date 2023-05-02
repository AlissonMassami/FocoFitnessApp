package com.focofitness.appexercicios.presentation.Treino

import androidx.lifecycle.ViewModel
import com.focofitness.appexercicios.domain.repository.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TreinoViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
): ViewModel(){
    val listaRotinaDiaria = localDataSource.buscarRotinas()


}