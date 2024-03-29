package com.focofitness.appexercicios.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.focofitness.appexercicios.util.Constants.TABLE_EXERCICIOS

@Entity(tableName = TABLE_EXERCICIOS)
data class Exercicios(
    @PrimaryKey()
    val exercicio: String,
    val image: String = "",
    val tipo: String,
    val series: Int,
    val repeticoes: Int
)