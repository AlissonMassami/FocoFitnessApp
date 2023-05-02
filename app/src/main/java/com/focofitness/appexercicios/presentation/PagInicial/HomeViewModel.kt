package com.focofitness.appexercicios.presentation.PagInicial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.focofitness.appexercicios.domain.model.Usuario
import com.focofitness.appexercicios.domain.repository.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
): ViewModel(){

    private val _novoUser: MutableStateFlow<Usuario?> = MutableStateFlow(null)
    var novoUser: StateFlow<Usuario?> = _novoUser
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _novoUser.value = localDataSource.buscarUsuario(1)
        }
    }


}