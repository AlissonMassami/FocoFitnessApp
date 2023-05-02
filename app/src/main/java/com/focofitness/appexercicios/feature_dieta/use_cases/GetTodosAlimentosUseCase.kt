package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.domain.repository.DietaRepository
import kotlinx.coroutines.flow.Flow

class GetTodosAlimentosUseCase (
    private val repository: DietaRepository
) {
    operator fun invoke(): Flow<List<TabelaCalorica>>{
        return repository.getTabelas()
    }
}