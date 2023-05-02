package com.focofitness.appexercicios.feature_dieta.data.repository

import com.focofitness.appexercicios.feature_dieta.data.local.TabelaDAO
import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.ListaAlimentos
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.domain.repository.DietaRepository
import kotlinx.coroutines.flow.Flow

class DietaRepositoryImpl (
    private val dao: TabelaDAO
        ): DietaRepository{
    override fun getTabelas(): Flow<List<TabelaCalorica>> {
        return dao.getTabelas()
    }

    override fun searchAlimento(alimento: String): Flow<List<TabelaCalorica>> {
        return dao.searchAlimento(alimento)
    }

    override suspend fun getAlimento(alimento: String): TabelaCalorica? {
        return dao.getAlimento(alimento)
    }

    override suspend fun insertAlimento(tabelaCalorica: TabelaCalorica) {
        dao.insertAlimento(tabelaCalorica)
    }

    override suspend fun deleteAlimento(tabelaCalorica: TabelaCalorica) {
        dao.deleteAlimento(tabelaCalorica)
    }

    override suspend fun searchDiaSemana(dia: String): CaloriasDiarias {
        return dao.searchDiaSemana(dia)
    }

    override suspend fun insertConsumoDiario(caloriasDiarias: CaloriasDiarias) {
        dao.insertConsumoDiario(caloriasDiarias)
    }

    override suspend fun insertAlimentoDiario(tabelaCalorica: TabelaCalorica) {
        dao.insertAlimento(tabelaCalorica)
    }

    override suspend fun deleteConsumoDiario(caloriasDiarias: CaloriasDiarias) {
        dao.deleteConsumoDiario(caloriasDiarias)
    }
}