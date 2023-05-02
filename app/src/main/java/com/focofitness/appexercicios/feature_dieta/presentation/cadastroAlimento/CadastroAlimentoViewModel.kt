package com.focofitness.appexercicios.feature_dieta.presentation.cadastroAlimento

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.use_cases.DietaUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CadastroAlimentoViewModel @Inject constructor(
    private val useCases: DietaUseCases
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private var _searchedAlimento = MutableStateFlow<List<TabelaCalorica>>(emptyList())
    val searchedAlimento = _searchedAlimento

    private var _alimentoSelecionado = mutableStateOf<TabelaCalorica?>(null)
    var alimentoSelecionado = _alimentoSelecionado

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchAlimento(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases
                .searchAlimentosUseCase(query = query)
                .collect { list ->
                    delay(500L)
                    _searchedAlimento.value = list
                }

        }
    }
    fun updateAliementoSelecionado(tabelaCalorica: TabelaCalorica){
        _alimentoSelecionado.value = tabelaCalorica

    }

    fun inserirConsumo(caloriasDiarias: CaloriasDiarias){
        viewModelScope.launch {
            useCases.inserirConsumoDiarioUseCase(caloriasDiarias)
        }
    }
    fun inserirAlimento(tabelaCalorica: TabelaCalorica){
        viewModelScope.launch {
            useCases.inserirAlimentoUseCase(tabelaCalorica)
        }
    }
    fun deleteConsumo(caloriasDiarias: CaloriasDiarias){
        viewModelScope.launch {
            useCases.deleteConsumoDiarioUseCase(caloriasDiarias)
        }
    }

}