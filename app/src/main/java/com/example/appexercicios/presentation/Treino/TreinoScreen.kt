package com.example.appexercicios.presentation.Treino

import android.content.ContentValues.TAG
import android.graphics.fonts.FontStyle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.appexercicios.component.Timer
import com.example.appexercicios.component.TopBar
import com.example.appexercicios.domain.model.Exercicios
import com.example.appexercicios.navegation.Screen
import com.example.appexercicios.presentation.PagExercicios.mostrarExerciciosListaBox
import com.example.appexercicios.ui.theme.LightBlue

@Composable
fun TreinoScreen(
        navController: NavHostController, 
        dia: String? = "QUI",
        treinoViewModel: TreinoViewModel = hiltViewModel()) 
{
    var listaRotinaDiaria = treinoViewModel.listaRotinaDiaria.collectAsState(initial = emptyList())

    var listaExercicios = emptyList<Exercicios>()

    listaRotinaDiaria.value.forEach {
        if(dia==it.dia) {
           listaExercicios = it.exercicios.exercicios
        }
    }

    var iniciarTreinoState = remember {
        mutableStateOf(false)
    }
    var intervaloEntreSeries = rememberSaveable {
        mutableStateOf("")
    }
    var intervaloEntreExercicios = rememberSaveable {
        mutableStateOf("")
    }
    val quantidadeExercicios = rememberSaveable{
        mutableStateOf(0)
    }
    var exercicioAtual = rememberSaveable {
        mutableStateOf(0)
    }
    val quantidadeSeries = rememberSaveable{
        mutableStateOf(0)
    }

    var inicioSerie = 1
    var fimDescanso = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopBar(
                iconeNavegacao = Icons.Filled.ArrowBack,
                tituloTopBar = "Treino",
                onclickVoltar = {
                    navController.navigate(Screen.Home.route)
                },
                iconeEditavel = Icons.Filled.Person,
                onClickAcao = {
                    //addState.value = !addState.value
                }
            )
        }) {
        Surface(
            Modifier
                .background(LightBlue)
                .fillMaxSize(),
            color = LightBlue
        ) {
            Column (modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                if (!iniciarTreinoState.value) {
                    LazyColumn() {
                        itemsIndexed(items = listaExercicios, itemContent = { index, exerc ->
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "${index + 1}º",
                                        style = MaterialTheme.typography.h5,
                                        modifier = Modifier.fillMaxHeight()
                                    )
                                    Spacer(modifier = Modifier.size(5.dp))
                                    Column() {
                                        Text(
                                            text = exerc.exercicio ,
                                            style = MaterialTheme.typography.h6
                                        )
                                        Text(
                                            text = "${exerc.series}x${exerc.repeticoes}",
                                            style = MaterialTheme.typography.subtitle1
                                        )
                                    }

                                }

                            }
                        })
                    }
                    Box(contentAlignment = Alignment.Center,
                        ) {
                        Column {
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = intervaloEntreSeries.value ,
                                onValueChange = { intervaloEntreSeries.value =
                                    it
                                },
                                label = { Text(text = "Intervalo Entre Séries (em seg)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = intervaloEntreExercicios.value ,
                                onValueChange = { intervaloEntreExercicios.value =
                                    it
                                },
                                label = { Text(text = "Intervalo Entre Exercicios (em seg)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                    }
                    Button(
                        onClick = {
                            if(intervaloEntreExercicios.value.isEmpty() || intervaloEntreSeries.value.isEmpty()){
                                Toast.makeText(
                                    context,
                                    "Preencha todos os campos antes de seguir",
                                    Toast.LENGTH_LONG)
                                    .show()
                            }else {
                                iniciarTreinoState.value = !iniciarTreinoState.value
                            }

                                  },
                        colors = ButtonDefaults.buttonColors(Color.Green)
                    ) {
                        Text(text = "INICIAR")

                    }

                }

                if (iniciarTreinoState.value){

                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(20.dp)) {
                        try {
                            quantidadeExercicios.value = listaExercicios.lastIndex
                            quantidadeSeries.value = listaExercicios[exercicioAtual.value].series
                        }catch (e: Exception){
                            Log.e(TAG, "TreinoScreen: $e")
                        }


                        if(exercicioAtual.value <= quantidadeExercicios.value){
                            Column (horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxSize()){
                                Text(text = "Série ${inicioSerie}/${quantidadeSeries.value} ",
                                    fontWeight = FontWeight.Bold,
                                    style =  MaterialTheme.typography.h5
                                )
                                Spacer(modifier = Modifier.size(10.dp))
                                Text(text = "Exercício ${exercicioAtual.value + 1}: ${listaExercicios[exercicioAtual.value].exercicio}",
                                        style =  MaterialTheme.typography.h5)
                                Spacer(modifier = Modifier.size(10.dp))

                                if (inicioSerie < quantidadeSeries.value) {
                                    Box(contentAlignment = Alignment.Center) {
                                        fimDescanso = Timer(
                                            totalTime = intervaloEntreSeries.value.toLong() * 1000L,
                                            handleColor = Color.Green,
                                            inactiveBarColor = Color.DarkGray,
                                            activeBarColor = Color(0xFF37B900),
                                            modifier = Modifier.size(200.dp)
                                        )
                                    }
                                    if (fimDescanso.value) {
                                        inicioSerie += 1
                                    }
                                } else {
                                    Box(contentAlignment = Alignment.Center) {
                                        fimDescanso = Timer(
                                            totalTime = intervaloEntreExercicios.value.toLong() * 1000L,
                                            handleColor = Color.Green,
                                            inactiveBarColor = Color.DarkGray,
                                            activeBarColor = Color(0xFF37B900),
                                            modifier = Modifier.size(200.dp)
                                        )
                                    }
                                    if (fimDescanso.value) {

                                            exercicioAtual.value += 1



                                        inicioSerie = 1
                                    }
                                }
                                Spacer(modifier = Modifier.size(10.dp))
                                Text(text = "Ao terminar aperte o botão para iniciar o tempo de descanso",
                                    style =  MaterialTheme.typography.body1,
                                textAlign = TextAlign.Center)
                                Spacer(modifier = Modifier.size(15.dp))
                                Text(
                                    textAlign = TextAlign.Center,
                                    text =
                                        if((exercicioAtual.value < quantidadeExercicios.value)) {
                                            "Próximo Exercício: ${listaExercicios[exercicioAtual.value + 1].exercicio}"
                                        }
                                        else "Último Exercício",
                                    style =  MaterialTheme.typography.h5)
                            }
                        }
                        else {
                            Text(text = "Parabéns! Treinamento do dia completo")
                        }



                    }



                }
            }

        }
    }
}

@Composable
fun timerDescanso(){
    Box(contentAlignment = Alignment.Center) {
        Timer(totalTime = 100L*1000L,
            handleColor = Color.Green,
            inactiveBarColor = Color.DarkGray,
            activeBarColor = Color(0xFF37B900),
            modifier = Modifier.size(200.dp)
        )
    }
}