package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.domain.repository.DietaRepository
import kotlinx.coroutines.flow.Flow

class SearchAlimentosUseCase (
    private val repository: DietaRepository
) {
    operator fun invoke(query: String): Flow<List<TabelaCalorica>> {
        return repository.searchAlimento(query)
    }
}