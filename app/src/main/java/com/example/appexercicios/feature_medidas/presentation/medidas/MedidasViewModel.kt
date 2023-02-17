package com.example.appexercicios.feature_medidas.presentation.medidas

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appexercicios.feature_medidas.domain.model.Medidas
import com.example.appexercicios.feature_medidas.domain.use_case.MedidasUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedidasViewModel @Inject constructor(
    private val medidasUseCases: MedidasUseCases
): ViewModel(){

    private var recentlyDeletedNotaMedida: Medidas? = null

    private var getNotasJob: Job? = null
    val listNotas = medidasUseCases.getTodasNotas()


    fun onEvent(event: NotasEvent){
        when(event){
            is NotasEvent.DeleteNote->{
                viewModelScope.launch {
                    medidasUseCases.deleteNota(event.medidas)
                    recentlyDeletedNotaMedida = event.medidas
                }
            }
            is NotasEvent.RestoreNote->{
                viewModelScope.launch {
                    medidasUseCases.inserirNota(recentlyDeletedNotaMedida ?: return@launch)
                    recentlyDeletedNotaMedida = null
                }
            }
        }
    }

}