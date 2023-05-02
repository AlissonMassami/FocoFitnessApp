package com.focofitness.appexercicios.data.local

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.focofitness.appexercicios.domain.model.ListaExercicios
import com.focofitness.appexercicios.feature_dieta.domain.model.ListaAlimentos
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type


class DatabaseConverter {


    @TypeConverter
    fun convertListExerciciostoJSON(listaExercicios: ListaExercicios): String{
        return Gson().toJson(listaExercicios)
    }
    @TypeConverter
    fun convertJSONtoListExercicios(json: String): ListaExercicios{
        return Gson().fromJson(json,ListaExercicios::class.java)
    }
//    @TypeConverter
//    fun fromListToString(list: ListaAlimentos): String {
//        return Gson().toJson(list)
//    }
//    @TypeConverter
//    fun toList(dataString: String): ListaAlimentos {
//        return Gson().fromJson(dataString, ListaAlimentos::class.java)
//    }

    @TypeConverter
    fun fromJSONtoList(json: String): List<TabelaCalorica> {
        val type: Type = object : TypeToken<List<TabelaCalorica>>() {}.type
        return Gson().fromJson(json,type)
    }
    @TypeConverter
    fun fromListTabelaToJSON(list: List<TabelaCalorica>): String {
        return Gson().toJson(list)
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