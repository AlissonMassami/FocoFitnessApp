package com.example.appexercicios.domain.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField


@Entity(tableName = "usuario_table")
data class Usuario(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    var nome: String,
    val peso: Float,
    val idade: Int,
    var foto: Bitmap?
)
