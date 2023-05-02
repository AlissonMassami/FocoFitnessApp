package com.example.appexercicios.presentation.medidas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.focofitness.appexercicios.component.TopBar
import com.focofitness.appexercicios.feature_medidas.presentation.medidas.MedidasViewModel
import com.focofitness.appexercicios.feature_medidas.presentation.medidas.NotasEvent
import com.focofitness.appexercicios.feature_medidas.presentation.medidas.components.NotaItem
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.ui.theme.MedBlue
import kotlinx.coroutines.launch

@Composable
fun MedidasScreen(
    navController : NavController,
    viewModel: MedidasViewModel = hiltViewModel()
){

    val lista = viewModel.listNotas.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        backgroundColor = MedBlue,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.MedidasEditar.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        topBar = {
            TopBar(
                iconeNavegacao = Icons.Filled.ArrowBack,
                tituloTopBar = "Suas Medidas",
                onclickVoltar = {
                    navController.navigate(Screen.Home.route)
                },
                iconeEditavel = Icons.Filled.Info,
                onClickAcao = {
                    navController.navigate(Screen.Perfil.route)
                }
            )},
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(lista.value) { note ->
                    NotaItem(
                        medidas = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.MedidasEditar.route +
                                            "?noteId=${note.id}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(NotasEvent.DeleteNote(note))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Anotação Apagada",
                                    actionLabel = "Desfazer"
                                )
                                if(result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NotasEvent.RestoreNote)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

}