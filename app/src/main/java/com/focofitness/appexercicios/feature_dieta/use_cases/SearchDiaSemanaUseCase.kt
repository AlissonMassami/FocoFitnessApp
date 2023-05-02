package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.ListaAlimentos
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.domain.repository.DietaRepository
import kotlinx.coroutines.flow.Flow

class SearchDiaSemanaUseCase (
    private val repository: DietaRepository
) {
    suspend operator fun invoke(dia: String): CaloriasDiarias? {
        return repository.searchDiaSemana(dia)
    }
}