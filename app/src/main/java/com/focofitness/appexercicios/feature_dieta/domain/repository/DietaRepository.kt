package com.focofitness.appexercicios.feature_dieta.domain.repository

import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.ListaAlimentos
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas
import kotlinx.coroutines.flow.Flow

interface DietaRepository {
    fun getTabelas(): Flow<List<TabelaCalorica>>

    fun searchAlimento(alimento: String): Flow<List<TabelaCalorica>>

    suspend fun getAlimento(alimento: String): TabelaCalorica?

    suspend fun insertAlimento(tabelaCalorica: TabelaCalorica)

    suspend fun deleteAlimento(tabelaCalorica: TabelaCalorica)

    suspend fun searchDiaSemana(dia: String): CaloriasDiarias?

    suspend fun insertConsumoDiario(caloriasDiarias: CaloriasDiarias)

    suspend fun insertAlimentoDiario(tabelaCalorica: TabelaCalorica)

    suspend fun deleteConsumoDiario(caloriasDiarias: CaloriasDiarias)

}