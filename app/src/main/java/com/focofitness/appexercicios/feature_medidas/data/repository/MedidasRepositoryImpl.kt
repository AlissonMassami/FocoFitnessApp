package com.focofitness.appexercicios.feature_medidas.data.repository

import com.focofitness.appexercicios.feature_medidas.data.data_source.MedidasDAO
import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas
import com.focofitness.appexercicios.feature_medidas.domain.repository.MedidasRepository
import kotlinx.coroutines.flow.Flow

class MedidasRepositoryImpl(
    private val dao : MedidasDAO
) : MedidasRepository {
    override fun getAnotacoesMedidas(): Flow<List<Medidas>> {
        return dao.getNotasMedidas()
    }

    override suspend fun getAnotacaoById(id: Int): Medidas? {
        return dao.getMedidaById(id)
    }

    override suspend fun insertNotaMedida(medidas: Medidas) {
        dao.insertNotaMedida(medidas)
    }

    override suspend fun deleteNotaMedida(medidas: Medidas) {
        dao.deleteNotaMedida(medidas)
    }

}