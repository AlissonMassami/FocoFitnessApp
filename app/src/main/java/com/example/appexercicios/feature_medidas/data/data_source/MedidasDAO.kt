package com.example.appexercicios.feature_medidas.data.data_source

import androidx.room.*
import com.example.appexercicios.feature_medidas.domain.model.Medidas
import com.example.appexercicios.navegation.Screen
import kotlinx.coroutines.flow.Flow

@Dao
interface MedidasDAO {

    @Query("SELECT * FROM medidas_table")
    fun getNotasMedidas(): Flow<List<Medidas>>

    @Query("SELECT * FROM medidas_table WHERE id = :id")
    suspend fun getMedidaById(id: Int): Medidas?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotaMedida(medidas: Medidas)

    @Delete
    suspend fun deleteNotaMedida(medidas: Medidas)
}