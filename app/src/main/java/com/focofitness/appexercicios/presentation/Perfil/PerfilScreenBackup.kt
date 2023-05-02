package com.focofitness.appexercicios.presentation.Perfil

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.focofitness.appexercicios.R
import com.focofitness.appexercicios.component.ImagemRedonda
import com.focofitness.appexercicios.component.TopBar
import com.focofitness.appexercicios.component.pickImageFromGallery

import com.focofitness.appexercicios.domain.model.Usuario
import com.focofitness.appexercicios.domain.util.getBitmapFromImage
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.ui.theme.DarkBlueTrans
import com.focofitness.appexercicios.ui.theme.LightBlue

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PerfilScreenBackup(
    navController: NavHostController ,
    perfilViewModell: PerfilViewModell = hiltViewModel()){

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }
    val bitmap = pickImageFromGallery(imageUri)

    val perfil = perfilViewModell.novoUser

    var idade by remember {
        mutableStateOf("0")
    }
    var peso by remember {
        mutableStateOf("0")
    }
    var nome by remember {
        mutableStateOf("Alisson")
    }
    perfil.let {
        nome = it.value?.nome ?: "Sem Cadastro"
        idade = it.value?.idade.toString()
        peso = it.value?.peso.toString()
    }
    val context = LocalContext.current
    val icon = getBitmapFromImage(context,R.mipmap.ic_profile_foreground)

    Scaffold(
        topBar = {
            TopBar(
                iconeNavegacao = Icons.Filled.ArrowBack,
                tituloTopBar = "Perfil",
                onclickVoltar = {
                    navController.navigate((Screen.Home.route))
                },
                iconeEditavel = Icons.Filled.Info,
                onClickAcao = {
                    navController.navigate(Screen.Perfil.route)
                }
            )
        }) {
        Surface(
            Modifier
                .background(LightBlue)
                .fillMaxSize() ,
            color = LightBlue
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally ,
                verticalArrangement = Arrangement.Center ,
                modifier = Modifier.fillMaxSize()
            ) {
                if(perfil.value?.foto != null){
                    if (bitmap != null) {
                        perfil.value!!.foto = bitmap.value
                    }
                    val foto = perfil.value!!.foto!!
                    Image(
                        bitmap = foto.asImageBitmap() ,
                        contentDescription = null ,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .clickable {
                                launcher.launch("image/*")
                            }
                            .border(width = 5.dp , color = DarkBlueTrans , shape = CircleShape) ,
                        contentScale =
                        if(foto.height>=foto.width){
                            ContentScale.FillWidth
                        }else {
                            ContentScale.FillHeight
                        },
                    )


                } else {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_profile_foreground) ,
                        contentDescription = null ,
                        contentScale = ContentScale.FillHeight ,
                        modifier = Modifier
                            .size(200.dp)
                            .clickable {
                                launcher.launch("image/*")
                            }

                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                TextField(
                    value = nome,
                    onValueChange = { nome  = it } ,
                    label = { Text(text = "Nome:") } ,

                    )
                Spacer(modifier = Modifier.size(10.dp))
                TextField(
                    value = peso ,
                    onValueChange = { peso = it } ,
                    label = { Text(text = "Peso(KG):") }
                )
                Spacer(modifier = Modifier.size(10.dp))
                TextField(
                    value = idade ,
                    onValueChange = { idade = it } ,
                    label = { Text(text = "Idade:") }
                )
                Button(onClick = {


                    val user = Usuario(
                        id = 1,
                        nome = nome ,
                        peso = peso.toFloat() ,
                        idade = idade.toInt() ,
                        foto = perfil.value?.foto ?: icon
                    )
                    perfilViewModell.atualizarPerfil(user)
                }) {
                    Text(text = "SALVAR")
                }

            }

        }
    }
}