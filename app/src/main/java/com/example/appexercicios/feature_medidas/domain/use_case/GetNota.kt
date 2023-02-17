package com.example.appexercicios.feature_medidas.domain.use_case

import com.example.appexercicios.feature_medidas.domain.model.Medidas
import com.example.appexercicios.feature_medidas.domain.repository.MedidasRepository
import kotlinx.coroutines.flow.Flow

class GetNota (
    private val repository: MedidasRepository
        ){

    suspend operator fun invoke(id: Int): Medidas? {
        return repository.getAnotacaoById(id)
    }
}