package com.example.appexercicios.data.local

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.example.appexercicios.domain.model.RotinaDiaria
import com.example.appexercicios.domain.model.ListaExercicios
import com.google.gson.Gson
import java.io.ByteArrayOutputStream

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListExerciciostoJSON(listaExercicios: ListaExercicios): String{
        return Gson().toJson(listaExercicios)
    }
    @TypeConverter
    fun convertJSONtoListExercicios(json: String): ListaExercicios{
        return Gson().fromJson(json,ListaExercicios::class.java)
    }

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray{

        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG , 50 , outputStream)
        return outputStream.toByteArray()
    }
    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap{
        return BitmapFactory.decodeByteArray(byteArray, 0,byteArray.size)
    }

}