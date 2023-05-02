package com.focofitness.appexercicios.presentation.CriarRotina

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.focofitness.appexercicios.component.TopBar
import com.focofitness.appexercicios.domain.model.Exercicios
import com.focofitness.appexercicios.domain.model.ListaExercicios
import com.focofitness.appexercicios.domain.model.RotinaDiaria
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.ui.theme.DarkBlueTrans2
import com.focofitness.appexercicios.ui.theme.LightBlue
import com.focofitness.appexercicios.util.TestTags
import java.util.*

@Composable
fun CriarRotinaScreen(
    navController: NavHostController,
    rotinaViewModel: CriarRotinaViewModel = hiltViewModel(),
    dia : String?
) {
    val listaExercicios = rotinaViewModel.listaExercicios.collectAsState(initial = emptyList())
    val listaRotinaDiaria = rotinaViewModel.listaRotinaDiaria.collectAsState(initial = emptyList())

    val addState = remember {
        mutableStateOf(false)
    }
    val novoNome = rememberSaveable {
        mutableStateOf("")
    }
    val novoTipo = rememberSaveable {
        mutableStateOf("")
    }
    val novoSeries = rememberSaveable {
        mutableStateOf("")
    }
    val novoRepeticoes = rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current

    val myList = rotinaViewModel.itemlist

    var lista = ListaExercicios(
        exercicios = emptyList()
    )

    listaRotinaDiaria.value.forEach { rotinaDiaria ->
        if(rotinaDiaria.dia == dia) {
            lista = rotinaDiaria.exercicios
        }
    }
    if(lista.exercicios.isNotEmpty()){
        myList.clear()
        myList.addAll(lista.exercicios)
    }



    Scaffold(
        topBar = {
            TopBar(
                iconeNavegacao = Icons.Filled.ArrowBack,
                tituloTopBar = "Criar Rotina",
                onclickVoltar = {
                    navController.navigate(Screen.Home.route)
                },
                iconeEditavel = Icons.Filled.Add,
                onClickAcao = {
                    addState.value = !addState.value
                }
            )
        }) {
        Surface(
            Modifier
                .background(LightBlue)
                .fillMaxSize(),
            color = LightBlue
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 5.dp , bottom = 10.dp , top = 0.dp , end = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (addState.value) {
                    Box(modifier = Modifier.fillMaxWidth(1f)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag(TestTags.NOME_EXERCICIO_TEXT_FIELD),
                                value = novoNome.value,
                                onValueChange = { novoNome.value = it },
                                label = { Text(text = "Nome Exercício") }
                            )
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag(TestTags.TIPO_EXERCICIO_TEXT_FIELD),
                                value = novoTipo.value,
                                onValueChange = { novoTipo.value = it },
                                label = { Text(text = "Tipo Exercicio") }
                            )
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag(TestTags.SETS_EXERCICIO_TEXT_FIELD),
                                value = novoSeries.value ,
                                onValueChange = { novoSeries.value = it },
                                label = { Text(text = "Nº Séries") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag(TestTags.REPS_EXERCICIO_TEXT_FIELD),
                                value = novoRepeticoes.value ,
                                onValueChange = { novoRepeticoes.value = it },
                                label = { Text(text = "Nº Repetições") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            Spacer(modifier = Modifier.size(15.dp))
                            ExtendedFloatingActionButton(
                                modifier = Modifier
                                    .width(200.dp)
                                    .testTag(TestTags.BOTAO_ADICIONAR_EXERCICIOS),
                                onClick = {
                                    if (novoNome.value.isNotBlank()){

                                        try {
                                            val exercicios = Exercicios(
                                                exercicio = novoNome.value ,
                                                tipo = novoTipo.value ,
                                                series = novoSeries.value.toInt() ,
                                                repeticoes = novoRepeticoes.value.toInt() ,
                                            )

                                            try {
                                                rotinaViewModel.addNovoExercicio(
                                                    exercicios
                                                )
                                                addState.value = false
                                            } catch (e: Exception) {
                                                Toast.makeText(
                                                    context ,
                                                    "Erro: $e" ,
                                                    Toast.LENGTH_LONG
                                                )
                                                    .show()
                                            }
                                        }catch (e: Exception){
                                            Toast.makeText(
                                                context,
                                                "Campo Séries e Reps devem conter somente números",
                                                Toast.LENGTH_LONG)
                                                .show()


                                        }
                                    }else{

                                        Toast.makeText(
                                            context,
                                            "Nome exercicio em branco",
                                            Toast.LENGTH_LONG)
                                            .show()
                                    }

                                },
                                icon = {
                                    Icon(
                                        Icons.Filled.Add,
                                        contentDescription = "Adicionar"
                                    )
                                },
                                text = { Text("Adicionar") }
                            )

                        }
                    }

                }
                Text(text = "Selecione a sequência de exercícios para $dia!",
                    style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Cursive),
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
                Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                    Text(text = "Exercícios:",
                    modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center

                        )
                    Text(text = "Rotina:",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                }
                Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                )
                {
                    Box(modifier = Modifier
                        .weight(1f)
                        .padding(10.dp)
                        //.border(BorderStroke(1.dp, Color.Black))
                        .fillMaxHeight()
                        .background(DarkBlueTrans2 , RoundedCornerShape(10.dp))


                    ) {
                        LazyColumn(modifier = Modifier
                            .testTag(TestTags.COLUNA_EXERCICIOS)
                            .fillMaxWidth()
                            .padding(top = 10.dp , end = 5.dp),
                        ) {
                            items(items = listaExercicios.value, itemContent = { exerc ->
                                val nomeExerc = exerc.exercicio.uppercase(Locale.getDefault())
                                Text(text = nomeExerc,
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .clickable {
                                            rotinaViewModel.addExercicioRotina(exerc)
                                        }
                                        .semantics {
                                            contentDescription = exerc.exercicio
                                        },

                                    )
                            }
                            )
                        }
                    }
                    Box(modifier = Modifier
                        .weight(1f)
                        .padding(10.dp)
                        //.border(BorderStroke(1.dp, Color.Black))
                        .fillMaxHeight()
                        .background(Color.Transparent , RoundedCornerShape(10.dp))


                    ) {
                        LazyColumn(modifier = Modifier
                            .testTag(TestTags.COLUNA_ROTINA)
                            .fillMaxWidth()
                            .padding(top = 10.dp , end = 5.dp),
                        ) {
                            itemsIndexed(items = myList) {index, exerc->
                                val nomeExerc = exerc.exercicio.uppercase(Locale.getDefault())
                                Text(
                                    text = "${index+1} º: $nomeExerc" ,
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .clickable {
                                            rotinaViewModel.removerExercicioRotina(exerc)
                                        } ,
                                )
                            }

                        }
                    }


                }
                
                Button(modifier = Modifier
                    .height(40.dp)
                    .testTag(TestTags.BOTAO_SALVAR_ROTINA),
                    onClick = {
                        val rotina = RotinaDiaria(
                            dia = dia.toString(),
                            exercicios = ListaExercicios(myList),
                            rotinaCompleta = false
                        )
                        rotinaViewModel.addNovaRotina(rotina)
                    }) {
                    Text(text = "Salvar")
                }


            }
        }
    }
}


