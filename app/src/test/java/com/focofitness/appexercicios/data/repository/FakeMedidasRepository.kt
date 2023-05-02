package com.focofitness.appexercicios.data.repository

import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas
import com.focofitness.appexercicios.feature_medidas.domain.repository.MedidasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMedidasRepository: MedidasRepository {

    private val notas = mutableListOf<Medidas>()
    override fun getAnotacoesMedidas(): Flow<List<Medidas>> {
        return flow { emit(notas) }
    }

    override suspend fun getAnotacaoById(id: Int): Medidas? {
        return notas.find { it.id == id }
    }

    override suspend fun insertNotaMedida(medidas: Medidas) {
        notas.add(medidas)
    }

    override suspend fun deleteNotaMedida(medidas: Medidas) {
        notas.remove(medidas)
    }

}