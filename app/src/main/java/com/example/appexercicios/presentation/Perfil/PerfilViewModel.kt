package com.example.appexercicios.presentation.Perfil

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appexercicios.R
import com.example.appexercicios.domain.model.Usuario
import com.example.appexercicios.domain.repository.LocalDataSource
import com.example.appexercicios.domain.util.getBitmapFromImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilViewModell @Inject constructor(
    private val localDataSource: LocalDataSource
): ViewModel(){

    //val buscarUsuario = localDataSource.buscarUsuario("1")
    private val _novoUser: MutableStateFlow<Usuario?> = MutableStateFlow(null)
    var novoUser: StateFlow<Usuario?> = _novoUser


    init {
        viewModelScope.launch(Dispatchers.IO) {
            _novoUser.value = localDataSource.buscarUsuario(1)
        }
    }

    fun atualizarPerfil(usuario: Usuario){

        try {
            viewModelScope.launch {
                localDataSource.addUsuario(usuario)
            }

        }catch (e: Exception){
            Log.e(TAG , "atualizarPerfilViewModel: $e" , )
        }
    }

}