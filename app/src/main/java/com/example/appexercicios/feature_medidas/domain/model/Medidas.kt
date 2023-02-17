package com.example.appexercicios.feature_medidas.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medidas_table")
data class Medidas(
    val title: String,
    val content: String,
    val timestamp: String,
    @PrimaryKey val id: Int? = null
)

class InvalidNoteException(message: String): Exception(message)