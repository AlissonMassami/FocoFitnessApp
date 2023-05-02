package com.focofitness.appexercicios.feature_dieta.presentation.DietaScreen

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
@OptIn(InternalCoroutinesApi::class)
@HiltViewModel
class DietaViewModel @Inject constructor(
    private val useCases: DietaUseCases
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private var _searchedAlimento = MutableStateFlow<List<TabelaCalorica>>(emptyList())
    val searchedAlimento = _searchedAlimento

    private var _alimentoSelecionado = mutableStateOf<TabelaCalorica?>(null)
    var alimentoSelecionado = _alimentoSelecionado

    private var _consumoDiario = mutableStateOf<CaloriasDiarias?>(null)
    var consumoDiario = _consumoDiario

    val itemConsumo = mutableStateListOf<TabelaCalorica>()


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
    fun addListaConsumo(tabelaCalorica: TabelaCalorica){
        itemConsumo.add(tabelaCalorica)
    }
    fun removeListaConsumo(tabelaCalorica: TabelaCalorica){
        itemConsumo.remove(tabelaCalorica)
    }
    fun clearListaConsumo(){
        itemConsumo.toList().forEach {
            itemConsumo.remove(it)
        }
    }

    fun searchDiaSemana(dia: String){
        viewModelScope.launch {
            _consumoDiario.value = useCases.searchDiaSemanaUseCase(dia)
        }
    }
    fun inserirConsumo(caloriasDiarias: CaloriasDiarias){
        viewModelScope.launch {
            useCases.inserirConsumoDiarioUseCase(caloriasDiarias)
        }
    }
    fun deleteConsumo(caloriasDiarias: CaloriasDiarias){
        viewModelScope.launch {
            useCases.deleteConsumoDiarioUseCase(caloriasDiarias)
        }
    }

}