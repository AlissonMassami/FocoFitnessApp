package com.example.appexercicios.feature_medidas.domain.use_case

import com.example.appexercicios.feature_medidas.domain.model.Medidas
import com.example.appexercicios.feature_medidas.domain.repository.MedidasRepository

import kotlinx.coroutines.flow.Flow

class GetTodasNotas (
    private val repository: MedidasRepository
) {
    operator fun invoke(): Flow<List<Medidas>> {
        return repository.getAnotacoesMedidas()
    }
}