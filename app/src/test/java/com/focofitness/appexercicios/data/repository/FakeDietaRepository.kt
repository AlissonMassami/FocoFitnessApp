package com.focofitness.appexercicios.data.repository

import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.domain.repository.DietaRepository
import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDietaRepository: DietaRepository {

    private val lista = mutableListOf<TabelaCalorica>()
    private val listaCal = mutableListOf<CaloriasDiarias>()

    override fun getTabelas(): Flow<List<TabelaCalorica>> {
        return flow { emit(lista) }
    }

    override fun searchAlimento(alimento: String): Flow<List<TabelaCalorica>> {
        return flow { emit(lista) }
    }

    override suspend fun getAlimento(alimento: String): TabelaCalorica? {
        return lista.find { it.alimento == alimento }
    }

    override suspend fun insertAlimento(tabelaCalorica: TabelaCalorica) {
        lista.add(tabelaCalorica)
    }

    override suspend fun deleteAlimento(tabelaCalorica: TabelaCalorica) {
        lista.remove(tabelaCalorica)
    }

    override suspend fun searchDiaSemana(dia: String): CaloriasDiarias? {
        return listaCal.find { it.diaSemana == dia }
    }

    override suspend fun insertConsumoDiario(caloriasDiarias: CaloriasDiarias) {
        listaCal.add(caloriasDiarias)
    }

    override suspend fun deleteConsumoDiario(caloriasDiarias: CaloriasDiarias) {
        listaCal.remove(caloriasDiarias)
    }
}