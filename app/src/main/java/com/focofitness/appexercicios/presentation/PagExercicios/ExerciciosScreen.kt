package com.focofitness.appexercicios.presentation.PagExercicios

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.focofitness.appexercicios.component.TopBar
import com.focofitness.appexercicios.domain.model.Exercicios
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.ui.theme.LightBlue
import com.focofitness.appexercicios.util.TestTags
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExerciciosScreen(
    navController: NavHostController,
    exerciciosViewModel: ExerciciosViewModel = hiltViewModel()
){
    var listaExercicios = exerciciosViewModel.listaExercicios.collectAsState(initial = emptyList())
    var addState = remember {
        mutableStateOf(false)
    }
    val novoNome = rememberSaveable{
        mutableStateOf("")
    }
    val novoTipo = rememberSaveable{
        mutableStateOf("")
    }
    val novoSeries = rememberSaveable{
        mutableStateOf("")
    }
    val novoRepeticoes = rememberSaveable{
        mutableStateOf("")
    }
    val context = LocalContext.current



    Scaffold(
        topBar = {
            TopBar(
                iconeNavegacao = Icons.Filled.ArrowBack,
                tituloTopBar = "Exercícios",
                onclickVoltar = {
                    navController.navigate(Screen.Home.route)
                                },
                iconeEditavel = Icons.Filled.Add,
                onClickAcao = {addState.value = !addState.value}
            )
        }) {
        Surface(
            Modifier
                .background(LightBlue)
                .fillMaxSize(),
            color = LightBlue
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp , bottom = 10.dp , top = 0.dp , end = 15.dp)) {
                if (addState.value) {

                    Box(modifier = Modifier.fillMaxWidth(1f)){
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag(TestTags.NOME_EXERCICIO_TEXT_FIELD),
                                value = novoNome.value,
                                onValueChange = { novoNome.value = it },
                                label = { Text(text = "Nome Exercício") },

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
                                value = novoSeries.value.toString(),
                                onValueChange = { novoSeries.value = it },
                                label = { Text(text = "Nº Séries") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )

                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag(TestTags.REPS_EXERCICIO_TEXT_FIELD),
                                value = novoRepeticoes.value.toString(),
                                onValueChange = { novoRepeticoes.value = it},
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
                                                      exerciciosViewModel.addNovoExercicio(
                                                          exercicios
                                                      )
                                                      addState.value = false
                                                      novoNome.value = ""
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
                LazyColumn(modifier = Modifier.fillMaxWidth()){
                    items(items = listaExercicios.value, itemContent = {exerc ->
                        Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            val nomeExerc = exerc.exercicio.uppercase(Locale.getDefault())
                            val tipoExer = exerc.tipo.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            }
                            Column(modifier = Modifier.weight(10f)) {
                                    Text(
                                        text = nomeExerc ,
                                        style = MaterialTheme.typography.h5
                                    )
                                    Text(
                                        text = " ${tipoExer} - Reps: ${exerc.series}x${exerc.repeticoes}" ,
                                        style = MaterialTheme.typography.subtitle1
                                    )
                            }


                            Box (modifier = Modifier.weight(1f),){
                                Row {
                                    IconButton(onClick = {
                                        exerciciosViewModel.deletarExercicio(exerc)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Delete ,
                                            contentDescription = null
                                        )

                                    }
                                }
                            }

                        }
                        Divider(color = Color.LightGray, thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally))
                    })
                }
            }
        }


    }
}


@Composable
fun mostrarExerciciosListaBox(
    exercicios: Exercicios,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit
){
    Box {
        Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()){
            Column (horizontalAlignment = Alignment.Start){
                Text(
                    text = exercicios.exercicio,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = "Musculo: ${exercicios.tipo} Reps: ${exercicios.series}x${exercicios.repeticoes}",
                    style = MaterialTheme.typography.subtitle1
                )
            }
            Row (horizontalArrangement = Arrangement.End){
                IconButton(onClick = { onClickEdit }) {
                    Icon(imageVector = Icons.Filled.Edit, contentDescription = null)

                }
                IconButton(onClick = { onClickDelete }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)

                }
            }

        }
    }

}