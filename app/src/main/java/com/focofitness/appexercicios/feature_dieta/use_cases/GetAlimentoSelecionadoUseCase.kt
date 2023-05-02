package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.domain.repository.DietaRepository
import kotlinx.coroutines.flow.Flow

class GetAlimentoSelecionadoUseCase (
    private val repository: DietaRepository
    ) {
        suspend operator fun invoke(alimento: String): TabelaCalorica?{
            return repository.getAlimento(alimento)
        }
}