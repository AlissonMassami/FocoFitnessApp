package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.ListaAlimentos
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.domain.repository.DietaRepository
import kotlinx.coroutines.flow.Flow

class InserirAlimentoUseCase (
    private val repository: DietaRepository
) {
    suspend operator fun invoke(tabelaCalorica: TabelaCalorica) {
        return repository.insertAlimento(tabelaCalorica)
    }
}