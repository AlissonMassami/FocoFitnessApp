package com.focofitness.appexercicios.feature_medidas.domain.repository


import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas
import kotlinx.coroutines.flow.Flow

interface MedidasRepository {
    fun getAnotacoesMedidas(): Flow<List<Medidas>>

    suspend fun getAnotacaoById(id: Int): Medidas?

    suspend fun insertNotaMedida(medidas: Medidas)

    suspend fun deleteNotaMedida(medidas: Medidas)

}