package com.example.appexercicios.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "rotina_database")
data class RotinaDiaria(
    @PrimaryKey(autoGenerate = false)
    val dia: String,
    val exercicios: ListaExercicios,
    val rotinaCompleta: Boolean = false
)
