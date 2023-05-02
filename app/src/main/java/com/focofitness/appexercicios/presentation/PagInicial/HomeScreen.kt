package com.focofitness.appexercicios.presentation.PagInicial

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

import com.focofitness.appexercicios.R
import com.focofitness.appexercicios.component.TopBarHomeScreen
import com.focofitness.appexercicios.domain.util.getBitmapFromImage
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.ui.theme.DarkBlue
import com.focofitness.appexercicios.ui.theme.DarkOrangeTrans
import com.focofitness.appexercicios.ui.theme.LightBlue
import com.focofitness.appexercicios.ui.theme.MedBlue
import com.focofitness.appexercicios.util.TestTags

import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter" , "StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
    ){

    var diaDaSemana = LocalDate.now().dayOfWeek.toString()

    when(diaDaSemana){
        "SUNDAY" -> {diaDaSemana = "DOM"}
        "MONDAY" -> {diaDaSemana = "SEG"}
        "TUESDAY" -> {diaDaSemana = "TER"}
        "WEDNESDAY" -> {diaDaSemana = "QUA"}
        "THURSDAY" -> {diaDaSemana = "QUI"}
        "FRIDAY" -> {diaDaSemana = "SEX"}
        "SATURDAY" -> {diaDaSemana = "SAB"}
    }

    var foto by remember { mutableStateOf<Bitmap?>(null) }

    val perfil = homeViewModel.novoUser
    var idade by remember {
        mutableStateOf("0")
    }
    var peso by remember {
        mutableStateOf("0")
    }
    var nome by remember {
        mutableStateOf("Sem Cadastro")
    }

    val context = LocalContext.current
    val icon = getBitmapFromImage(context, R.mipmap.ic_profile_foreground)

    perfil.value?.let {
        nome = it.nome ?: "Sem Cadastro"
        foto = it.foto
        idade = it.idade.toString()
        peso = it.peso.toString()
    }

    Scaffold(
        topBar = {
            TopBarHomeScreen(
                iconeNavegacao = Icons.Filled.Menu,
                tituloTopBar = "Bem Vindo!",
                onclickVoltar = {//TODO
                    },
                iconeEditavel = Icons.Filled.Person,
                onClickAcao = {
                    navController.navigate(Screen.Perfil.route)
                }
            )
        }) {
        Surface(
            Modifier
                .background(LightBlue)
                .fillMaxSize(),
            color = LightBlue) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 5.dp , bottom = 10.dp , top = 0.dp , end = 5.dp),
                horizontalAlignment = Alignment.End
            ) {
                Card(
                    modifier = Modifier
                        .padding(top = 20.dp , start = 10.dp , end = 8.dp)
                        .fillMaxWidth()// adding some space to the label
                        //.background(color = DarkBlueTrans2 , shape = RoundedCornerShape(15.dp))
                        .height(130.dp)
                        .align(CenterHorizontally),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(15.dp),



                ){
                    val boxHeight = with(LocalDensity.current){10.dp.toPx()}
                    val boxWidth = with(LocalDensity.current){450.dp.toPx()}
                    Box(modifier = Modifier
                        .fillMaxSize()
                        //.background(color = DarkBlueTrans2 , shape = RoundedCornerShape(15.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(MedBlue , DarkBlue) ,
                                start = Offset(0f , 0f) ,//topleft
                                end = Offset(boxWidth , boxHeight)
                            )
                        )
                    ){
                        Row(
                            modifier = Modifier.padding(16.dp) ,
                            verticalAlignment = Alignment.CenterVertically ,
                            horizontalArrangement = Arrangement.Start ,

                            ) {

                            if(foto == null || foto == icon) {
                                Image(
                                    painter = painterResource(id = R.mipmap.ic_profile_foreground) ,
                                    contentDescription = "imagem_perfil_padrao" ,
                                    contentScale = ContentScale.FillHeight ,
                                    modifier = Modifier
                                        .size(100.dp)

                                )
                            } else {
                                Image(
                                    bitmap = foto!!.asImageBitmap() ,
                                    contentDescription = "imagem_perfil_user" ,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape)
                                        .border(
                                            width = 1.dp ,
                                            color = Color.Black ,
                                            shape = CircleShape
                                        ) ,
                                    contentScale = ContentScale.FillWidth ,

                                    )
                            }
                            Spacer(modifier = Modifier.size(15.dp))
                            Column(
                                horizontalAlignment = CenterHorizontally
                            ) {
                                Text(
                                    text = "$nome" ,
                                    style = MaterialTheme.typography.h5 ,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.size(10.dp))
                                Row(Modifier.padding(start = 20.dp)) {
                                    Column(horizontalAlignment = CenterHorizontally) {
                                        Text(
                                            text = "${peso}" ,
                                            style = MaterialTheme.typography.subtitle1 ,
                                            fontWeight = FontWeight.Bold ,

                                            )
                                        Text(
                                            text = "KG" ,
                                            style = MaterialTheme.typography.subtitle1 ,

                                            )
                                    }
                                    Spacer(modifier = Modifier.size(15.dp))
                                    Column(horizontalAlignment = CenterHorizontally) {
                                        Text(
                                            text = "${idade}" ,
                                            style = MaterialTheme.typography.subtitle1 ,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "ANOS" ,
                                            style = MaterialTheme.typography.subtitle1 ,
                                        )
                                    }
                                }
                            }
                        }




                    }
                }
                Column (horizontalAlignment = CenterHorizontally){
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Column(modifier = Modifier.padding(15.dp)) {
                            Row {

                                Column(horizontalAlignment = CenterHorizontally) {
                                    Card(
                                        elevation = 15.dp ,
                                        shape = CircleShape
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.mipmap.ic_exercicios_foreground) ,
                                            contentDescription = "icone_exercicio" ,
                                            contentScale = ContentScale.FillHeight ,
                                            alpha = 1.5f ,
                                            modifier = Modifier
                                                .size(130.dp)
                                                .clickable {
                                                    navController.navigate(Screen.PagExercicios.route)
                                                }

                                        )
                                    }
                                    Spacer(modifier = Modifier.size(5.dp))
                                    Text(text = "Exercícios")
                                }

                                Spacer(modifier = Modifier.size(15.dp))
                                Column (horizontalAlignment = CenterHorizontally){
                                    Card(
                                        elevation = 15.dp ,
                                        shape = CircleShape
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.mipmap.ic_dieta_foreground) ,
                                            contentDescription = "icone_dieta" ,
                                            alpha = 1.5f ,
                                            contentScale = ContentScale.FillHeight ,
                                            modifier = Modifier
                                                .size(130.dp)
                                                .clickable {
                                                    navController.navigate(Screen.Dieta.route)
                                                }

                                        )
                                    }
                                    Spacer(modifier = Modifier.size(5.dp))
                                    Text(text = "Controle Dieta")
                                }
                            }
                            Spacer(modifier = Modifier.size(20.dp))
                            Row {
                                Column (horizontalAlignment = CenterHorizontally){
                                    Card(
                                        elevation = 15.dp ,
                                        shape = CircleShape
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.mipmap.ic_musculacao_foreground) ,
                                            contentDescription = "icone_treino" ,
                                            alpha = 1.5f ,
                                            contentScale = ContentScale.FillHeight ,
                                            modifier = Modifier
                                                .size(130.dp)
                                                .clickable {
                                                    navController.navigate("iniciar_treino/${diaDaSemana}")
                                                }

                                        )
                                    }
                                    Spacer(modifier = Modifier.size(5.dp))
                                    Text(text = "Iniciar Treino")
                                }
                                Spacer(modifier = Modifier.size(15.dp))
                                Column (horizontalAlignment = CenterHorizontally){
                                    Card(
                                        elevation = 15.dp ,
                                        shape = CircleShape
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.mipmap.ic_medidas_foreground) ,
                                            contentDescription = "icone_medidas" ,
                                            alpha = 1.5f ,
                                            contentScale = ContentScale.FillHeight ,
                                            modifier = Modifier
                                                .size(130.dp)
                                                .clickable {
                                                    navController.navigate(Screen.MedidasTela.route)
                                                }

                                        )
                                    }
                                    Spacer(modifier = Modifier.size(5.dp))
                                    Text(text = "Anotações")
                                }
                            }
                        }
                    }
                    Text(
                        text = "Criar Nova rotina",
                        style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive),

                        )
                    Spacer(modifier = Modifier.size(5.dp))
                    Divider(
                        color = Color.LightGray, thickness = 1.dp,
                        modifier = Modifier
                            .width(300.dp)
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                        boxRotina(dia = "DOM" , navController , TestTags.ROTINA_DOM)
                        Spacer(modifier = Modifier.size(5.dp))
                        boxRotina(dia = "SEG" , navController ,TestTags.ROTINA_SEG)
                        Spacer(modifier = Modifier.size(5.dp))
                        boxRotina(dia = "TER" , navController ,TestTags.ROTINA_TER)
                        Spacer(modifier = Modifier.size(5.dp))
                        boxRotina(dia = "QUA" , navController ,TestTags.ROTINA_QUA)
                        Spacer(modifier = Modifier.size(5.dp))
                        boxRotina(dia = "QUI" , navController ,TestTags.ROTINA_QUI)
                        Spacer(modifier = Modifier.size(5.dp))
                        boxRotina(dia = "SEX" , navController ,TestTags.ROTINA_SEX)
                        Spacer(modifier = Modifier.size(5.dp))
                        boxRotina(dia = "SAB" , navController ,TestTags.ROTINA_SAB)
                    }
                }



            }
        }

    }
}

@Composable
fun boxRotina(dia: String , navController: NavHostController , testTag: String){
    Box(modifier = Modifier
        .background(DarkOrangeTrans , RoundedCornerShape(5.dp))
        .width(45.dp)
        .height(65.dp)
        .clickable {
            navController.navigate("criar_rotina_screen/${dia}")
        }
        .testTag(testTag),

        contentAlignment = Alignment.Center

    ) {
        Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
        ) {
            Text(text = dia,
            fontWeight = FontWeight.Bold
            )
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")

        }
    }

}



























