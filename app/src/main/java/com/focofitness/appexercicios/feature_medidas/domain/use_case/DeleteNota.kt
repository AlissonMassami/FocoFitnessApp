package com.focofitness.appexercicios.feature_medidas.domain.use_case

import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas
import com.focofitness.appexercicios.feature_medidas.domain.repository.MedidasRepository

class DeleteNota(
    private val repository: MedidasRepository
) {
    suspend operator fun invoke(medidas: Medidas){
        repository.deleteNotaMedida(medidas)
    }
}