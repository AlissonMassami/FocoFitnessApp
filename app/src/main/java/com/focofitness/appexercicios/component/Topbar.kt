package com.focofitness.appexercicios.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.focofitness.appexercicios.ui.theme.MedOrange
import kotlin.system.exitProcess

@Composable
fun TopBar(iconeNavegacao: ImageVector,
           tituloTopBar:String,
           onclickVoltar: () -> Unit,
           iconeEditavel: ImageVector,
           backgroundColor : Color = MedOrange,
           onClickAcao: () -> Unit) {

    var expanded by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        backgroundColor = backgroundColor,
        title = { Text(tituloTopBar) },
        navigationIcon =
        {
            IconButton(onClick =
                onclickVoltar
            ) {
                Icon(
                    iconeNavegacao ,
                    contentDescription = "botao_voltar",
                )
                if(iconeNavegacao == Icons.Filled.Menu){
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                exitProcess(-1) }
                        ) {
                            Text(text = "Sair")
                        }
                    }
                }

            }
        },

        actions = {
            // RowScope here, so these icons will be placed horizontally
            IconButton(onClick = onClickAcao) {
                Icon(iconeEditavel, contentDescription = "botao_direito")
            }
        }
    )
}

@Composable
fun TopBarHomeScreen(iconeNavegacao: ImageVector,
           tituloTopBar:String,
           onclickVoltar: () -> Unit,
           iconeEditavel: ImageVector,
           backgroundColor : Color = MedOrange,
           onClickAcao: () -> Unit) {

    var expanded by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        backgroundColor = backgroundColor,
        title = { Text(tituloTopBar) },
        navigationIcon =
        {
            IconButton(onClick =
            { expanded = true }
            ) {
                Icon(
                    iconeNavegacao ,
                    contentDescription = "botao_voltar",
                )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                exitProcess(-1) }
                        ) {
                            Text(text = "Sair")
                        }
                    }


            }
        },

        actions = {
            // RowScope here, so these icons will be placed horizontally
            IconButton(onClick = onClickAcao) {
                Icon(iconeEditavel, contentDescription = "botao_direito")
            }
        }
    )
}
