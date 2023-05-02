package com.focofitness.appexercicios.feature_dieta.data.local

import androidx.room.*
import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.ListaAlimentos
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas
import kotlinx.coroutines.flow.Flow

@Dao
interface TabelaDAO {

    @Query("SELECT * FROM tabela_caloria")
    fun getTabelas(): Flow<List<TabelaCalorica>>

    @Query("SELECT * FROM tabela_caloria WHERE alimento LIKE '%' || :alimento || '%'")
    fun searchAlimento(alimento: String): Flow<List<TabelaCalorica>>

    @Query("SELECT * FROM tabela_caloria WHERE alimento = :alimento")
    suspend fun getAlimento(alimento: String): TabelaCalorica?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlimento(tabelaCalorica: TabelaCalorica)

    @Delete
    suspend fun deleteAlimento(tabelaCalorica: TabelaCalorica)

    @Query("SELECT * FROM calorias_diarias WHERE diaSemana = :dia")
    suspend fun searchDiaSemana(dia: String): CaloriasDiarias

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsumoDiario(caloriasDiarias: CaloriasDiarias)

    @Delete
    suspend fun deleteConsumoDiario(caloriasDiarias: CaloriasDiarias)




}