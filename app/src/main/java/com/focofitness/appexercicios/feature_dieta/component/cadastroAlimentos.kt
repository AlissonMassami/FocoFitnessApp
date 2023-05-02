package com.focofitness.appexercicios.feature_dieta.component

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun cadastroAlimentos(context: Context): List<TabelaCalorica> {

    lateinit var jsonString: String
    try {
        jsonString = context.assets.open("tabela/tabelacalorica.json")
            .bufferedReader()
            .use { it.readText() }
    } catch (ioException: IOException) {
       //todo
    }

    val listCountryType = object : TypeToken<List<TabelaCalorica>>() {}.type
    return Gson().fromJson(jsonString, listCountryType)
}
//here Gson() is basically providing fromJson methods which actually //deserializing json. It basically parse json string to //list<Country> object
//this getCountryCode(ctx: Context) will return a list of Country data class.
