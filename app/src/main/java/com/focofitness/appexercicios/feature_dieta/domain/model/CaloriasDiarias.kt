package com.focofitness.appexercicios.feature_dieta.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.focofitness.appexercicios.domain.model.ListaExercicios

@Entity(tableName = "calorias_diarias")
data class CaloriasDiarias (
    @PrimaryKey(autoGenerate = false) var diaSemana : String,
    val listaAlimentos: List<TabelaCalorica>,
)