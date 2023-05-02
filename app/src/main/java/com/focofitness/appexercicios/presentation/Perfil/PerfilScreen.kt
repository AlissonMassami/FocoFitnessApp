package com.focofitness.appexercicios.presentation.Perfil

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.focofitness.appexercicios.R

import com.focofitness.appexercicios.component.TopBar
import com.focofitness.appexercicios.component.pickImageFromGallery
import com.focofitness.appexercicios.domain.model.Usuario
import com.focofitness.appexercicios.domain.util.getBitmapFromImage
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.presentation.Perfil.permissions.cameraPermissionState
import com.focofitness.appexercicios.presentation.Perfil.permissions.galleryPermissionState
import com.focofitness.appexercicios.ui.theme.DarkBlue
import com.focofitness.appexercicios.ui.theme.LightBlue
import com.focofitness.appexercicios.ui.theme.MedBlue
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalPermissionsApi
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PerfilScreen(
        navController: NavHostController ,
        perfilViewModell: PerfilViewModell = hiltViewModel()){

    val context = LocalContext.current
    val icon = getBitmapFromImage(context, R.mipmap.ic_profile_foreground)
    var resultBitmap: Bitmap? by rememberSaveable { mutableStateOf(icon) }
    val perfil = perfilViewModell.novoUser
    var bitmap = remember { mutableStateOf<Bitmap?>(null) }


    val launcherForImageCapture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) {
         perfil.value?.foto = if(it.toString().isEmpty()) {
            icon
        } else {
            it
        }
    }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcherForImageGallery =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            imageUri = uri
        }
    bitmap = pickImageFromGallery(imageUri)

    var idade by remember {
        mutableStateOf("0")
    }
    var peso by remember {
        mutableStateOf("0")
    }
    var nome by remember {
        mutableStateOf("Seu Nome")
    }


    perfil.value?.let {
            nome = it.nome
            idade = it.idade.toString()
            peso = it.peso.toString()
    }
    val cameraPermission = cameraPermissionState()
    val galleryPermission = galleryPermissionState()
    var openDialog by remember {
        mutableStateOf(false) // Initially dialog is closed
    }
    if(openDialog){
        CameraGaleriaPopUp (
            onCameraClick = {
                openDialog = false
                if(cameraPermission.hasPermission) {
                    launcherForImageCapture.launch()
                } else if(!cameraPermission.hasPermission) {
                    cameraPermission.launchPermissionRequest()
                }
            },
            onGaleriaClick = {
                openDialog = false
                if(galleryPermission.hasPermission) {
                    launcherForImageGallery.launch("image/*")
                } else if(!galleryPermission.hasPermission) {
                    galleryPermission.launchPermissionRequest()

                }

            }){
            openDialog = false
        }
    }
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

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .clip(shape = CircleShape)
                        .border(
                            width = 1.dp ,
                            color = DarkBlue ,
                            shape = CircleShape
                        )
                ) {
                    if(perfil.value?.foto != null){
                        if (bitmap.value != null) {
                            perfil.value!!.foto = bitmap.value
                        }
                        val foto = perfil.value!!.foto!!

                        Image(
                            bitmap = foto.asImageBitmap(),
                            contentDescription = "Captured image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(200.dp)
                                .clickable {
                                    openDialog = true

                                }
                        )
                    }
                    else {
                        Image(
                            bitmap = icon.asImageBitmap(),
                            contentDescription = "Captured image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(200.dp)
                                .clickable {
                                    openDialog = true

                                }
                        )
                    }

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

@Composable
fun CameraGaleriaPopUp(
    onCameraClick: () -> Unit,
    onGaleriaClick: () -> Unit,
    onDismiss: () -> Unit){

    val contextForToast = LocalContext.current.applicationContext
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier
                .width(250.dp)
                .height(150.dp),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MedBlue) ,
                    contentAlignment = Alignment.Center
                ) {
                    Column (modifier = Modifier.padding(bottom = 10.dp) ){
                        Text(
                            modifier = Modifier.padding(top = 16.dp , bottom = 16.dp) ,
                            text = "SELECIONAR FOTO:" ,
                            textAlign = TextAlign.Center ,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold ,
                                fontSize = 20.sp
                            )
                        )
//                        Divider(Modifier.fillMaxWidth()
//                            .size(1.dp))
                        Row {
                            Button(
                                modifier = Modifier.border(
                                    2.dp,
                                    Color.Black,
                                    CircleShape,
                                    ),
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White
                                ) ,
                                onClick = {
                                    onCameraClick()
                                }) {
                                Image(
                                    painter = painterResource(id = R.mipmap.ic_camera2_foreground) ,
                                    contentDescription = "Enable Camera"
                                )

                            }
                            Spacer(modifier = Modifier.size(10.dp))
                            Button(
                                modifier = Modifier
                                    .border(
                                        2.dp ,
                                        Color.Black ,
                                        CircleShape ,
                                    ),
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White
                                ) ,
                                onClick = {
                                    onGaleriaClick()
                                }) {
                                Image(
                                    painter = painterResource(id = R.mipmap.ic_gallery_foreground) ,
                                    contentDescription = "Enable Camera"
                                )

                            }
                        }


                    }


                }
            }
        }


    }

}


