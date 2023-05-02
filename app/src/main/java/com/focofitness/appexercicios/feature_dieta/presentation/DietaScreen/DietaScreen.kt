package com.focofitness.appexercicios.feature_dieta.presentation.DietaScreen

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.focofitness.appexercicios.component.TopBar
import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.presentation.component.DropdownList
import com.focofitness.appexercicios.feature_dieta.presentation.component.ListaRefeicaoDiaria
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.ui.theme.DarkBlue
import com.focofitness.appexercicios.ui.theme.DarkBlueTrans2
import com.focofitness.appexercicios.ui.theme.LightBlue
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalCoilApi
@Composable
fun DietaScreen(
    dietaViewModel: DietaViewModel = hiltViewModel() ,
    navController: NavController
) {
    var searchQuery by dietaViewModel.searchQuery
    val alimento = dietaViewModel.searchedAlimento.collectAsState(emptyList())
    var alimentoSelecionado by dietaViewModel.alimentoSelecionado
    var alimentoEditar = remember {
        mutableStateOf<TabelaCalorica?>(null)
    }


    val systemUiController = rememberSystemUiController()
    val systemBarColor = Color.Blue
    val mostrarLista = remember {
        mutableStateOf(false)
    }
    val mostrarAlimento = remember {
        mutableStateOf(false)
    }
    val procurarState = remember {
        mutableStateOf(false)
    }
    val editarQuantidade = remember {
        mutableStateOf(false)
    }
    val naoAtualizar = remember {
        mutableStateOf(false)
    }
    val diaString = remember {
        mutableStateOf("")
    }
    val diaDisplay = remember {
        mutableStateOf("")
    }
    val consumoDiario = remember {
        mutableStateOf(0.0)
    }
    val consumoCarb = remember {
        mutableStateOf(0.0)
    }
    val consumoProt = remember {
        mutableStateOf(0.0)
    }
    val consumoGord = remember {
        mutableStateOf(0.0)
    }
    val novaQtde = remember { TextFieldState() }

    var listaRefeicao = dietaViewModel.itemConsumo

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }
    val context = LocalContext.current


    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    var dia = remember {
        mutableStateOf<LocalDate>(LocalDate.now())
    }
    if(dia.value == LocalDate.now()){
        diaString.value = dia.value.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"))
    }
    var consumoAtual = dietaViewModel.consumoDiario
    dietaViewModel.searchDiaSemana(diaString.value)



    if(listaRefeicao.isEmpty() && diaString.value.isNotBlank() && !naoAtualizar.value){
        dietaViewModel.clearListaConsumo()

        consumoAtual.value?.listaAlimentos?.forEach {
            dietaViewModel.addListaConsumo(it)
        }
        consumoDiario.value= 0.0
        consumoCarb.value= 0.0
        consumoProt.value= 0.0
        consumoGord.value= 0.0

        mostrarAlimento.value = true



    }
    if(consumoDiario.value == 0.0 && !naoAtualizar.value){
        listaRefeicao.forEach { alimento ->
            consumoDiario.value += alimento.caloria?.toDouble() ?: 0.0
            if(alimento.carboidrato?.isBlank() == false){
                consumoCarb.value += alimento.carboidrato?.toDouble() ?: 0.0
            }
            if(alimento.proteina?.isBlank() == false){
                consumoProt.value += alimento.proteina?.toDouble() ?: 0.0
            }
            if(alimento.lipidios?.isBlank() == false){
                consumoGord.value += alimento.lipidios?.toDouble() ?: 0.0
            }
        }


    }




    Scaffold(
        topBar = {
            if(!procurarState.value){
                TopBar(
                    iconeNavegacao = Icons.Filled.ArrowBack,
                    tituloTopBar = "Macros",
                    onclickVoltar = {
                        navController.navigate(Screen.Home.route)
                    },
                    iconeEditavel = Icons.Filled.Search,
                    onClickAcao = {
                        procurarState.value = true
                    }
                )
            }else{
                SearchTopBar(
                    text = searchQuery,
                    onTextChange = {
                        dietaViewModel.updateSearchQuery(query = it)

                    },
                    onSearchClicked = {
                        dietaViewModel.searchAlimento(query = it)
                        mostrarLista.value = true
                            expanded = true
                    },
                    onCloseClicked = {
                        procurarState.value = false
                    }
                )
            }


        }) {
        Surface(
            Modifier
                .background(LightBlue)
                .fillMaxSize(),
            color = LightBlue
        ) {


            Column(verticalArrangement = Arrangement.Top) {
                DropdownList(

                    expanded = expanded ,
                    selectedIndex = selectedIndex ,
                    items = alimento ,
                    onSelect = { alimento , index ->
                        selectedIndex = index

                        dietaViewModel.updateAliementoSelecionado(alimento)
                       // dietaViewModel.addListaConsumo(alimento)
                        alimentoEditar.value = alimento
                        editarQuantidade.value = true
                        mostrarLista.value = false
                        mostrarAlimento.value = true
                        procurarState.value = false
                        searchQuery = ""
                        expanded = false


                    } ,
                    onDismissRequest = {
                        expanded = false
                    }) {


                }
            }


                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray) ,
                    contentAlignment = Alignment.Center

                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            dietaViewModel.clearListaConsumo()
                            dia.value = dia.value.minusDays(1)
                            diaString.value = dia.value.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"))
                            dietaViewModel.searchDiaSemana(diaString.value)
                            naoAtualizar.value = false

                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack ,
                                contentDescription = "Seta Voltar Dia"
                            )
                        }
                        if(dia.value==LocalDate.now()){
                            diaDisplay.value = "Hoje"
                        }else if(dia.value==(LocalDate.now().minusDays(1))){
                            diaDisplay.value = "Ontem"
                        }else if(dia.value==(LocalDate.now().plusDays(1))){
                            diaDisplay.value = "Amanhã"
                        }else{
                            diaDisplay.value = diaString.value
                        }
                        Text(text = diaDisplay.value,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = {
                            dietaViewModel.clearListaConsumo()
                            dia.value = dia.value.plusDays(1)
                            diaString.value = dia.value.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"))
                            dietaViewModel.searchDiaSemana(diaString.value)
                            naoAtualizar.value = false
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowForward ,
                                contentDescription = "Seta Voltar Dia"
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.size(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = consumoDiario.value.toInt().toString(),
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = "KCAL",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Carb: ${consumoCarb.value.toInt()}g",
                        style = MaterialTheme.typography.h6,
                        color = Color.Red)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = "Prot: ${consumoProt.value.toInt()}g",
                        style = MaterialTheme.typography.h6,
                        color = Color.Blue)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = "Gord: ${consumoGord.value.toInt()}g",
                        style = MaterialTheme.typography.h6,
                        color = Color.Green)
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = "Refeições",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive) ,

                    )
                Spacer(modifier = Modifier.size(10.dp))

                Column(verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 10.dp)) {
                    Column (Modifier.weight(if(editarQuantidade.value) 4f else 0.001f)){
                        if(editarQuantidade.value) {
                            novaQtde.text = alimentoEditar.value?.quantidade.toString()
                            alimentoEditar.value?.let { it1 ->
                                BoxEditItem(item = it1 , novaQtde , context , onCancel = {
                                    editarQuantidade.value = false
                                }) { novoItem ->
                                    if(novaQtde.text.isNotBlank()) {
                                        consumoDiario.value = 0.0
                                        consumoCarb.value = 0.0
                                        consumoGord.value = 0.0
                                        consumoProt.value = 0.0
                                        listaRefeicao.remove(alimentoEditar.value)
                                        listaRefeicao.add(novoItem)
                                    }
                                    editarQuantidade.value = false
                                }
                            }
                        }

                    }

                if(mostrarAlimento.value) {
                    Box(
                        modifier = Modifier
                            .weight(if(editarQuantidade.value) 5f else 9f)
                            .padding(10.dp)
                            //.border(BorderStroke(1.dp, Color.Black))
                            .background(DarkBlueTrans2 , RoundedCornerShape(10.dp))


                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)

                        ) {
                            items(items = listaRefeicao , itemContent = { item ->
                                ListaRefeicaoDiaria(
                                    item = item ,
                                    onClickEditRefeicao = {
                                        alimentoEditar.value = item
                                        editarQuantidade.value = true
                                    }
                                ) {
                                    dietaViewModel.removeListaConsumo(item)
                                    naoAtualizar.value = true
                                    consumoDiario.value -= item.caloria?.toDouble() ?: 0.0
                                    if(item.carboidrato?.isBlank() == false) {
                                        consumoCarb.value -= item.carboidrato?.toDouble() ?: 0.0
                                    }
                                    if(item.proteina?.isBlank() == false) {
                                        consumoProt.value -= item.proteina?.toDouble() ?: 0.0
                                    }
                                    if(item.lipidios?.isBlank() == false) {
                                        consumoGord.value -= item.lipidios?.toDouble() ?: 0.0
                                    }
                                }
                            })
                        }

                    }
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                        try {
                            dietaViewModel.inserirConsumo(
                                CaloriasDiarias(
                                    diaSemana = diaString.value ,
                                    listaAlimentos = listaRefeicao
                                )
                            )
                        } catch (e: Exception) {
                            Log.e(TAG , "Erro salvar: $e")
                        }

                    }) {
                        Row {
                            Icon(imageVector = Icons.Filled.Star , contentDescription = null)
                            Text("Salvar")
                        }
                    }
                    Text(text = "+ Criar novo alimento",
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.CadastroAlimento.route)
                        }.padding(15.dp),
                        color = DarkBlue
                    )
                }
            }
        }
    }

}

fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}

fun Modifier.bottomBorder(strokeWidth: Dp , color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx/2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height) ,
                end = Offset(x = width , y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)

@Composable
fun BoxEditItem(
    item: TabelaCalorica ,
    novaQtde: TextFieldState = remember { TextFieldState() } ,
    context: Context ,
    onCancel: () -> Unit ,
    onClickAtt: (novoItem: TabelaCalorica) -> Unit
){

    var novoCal = remember {
        mutableStateOf(0.0)
    }
    val novoCarb = remember {
        mutableStateOf(0.0)
    }
    val novoProt = remember {
        mutableStateOf(0.0)
    }
    val novoGord = remember {
        mutableStateOf(0.0)
    }

    try {
        novoCal.value = (item.caloria?.toDouble()?.times(novaQtde.text.toDouble())?.div(
            item.quantidade?.toDouble()
            ?: 100.0)) ?: 0.0
        novoCarb.value = (item.carboidrato?.toDouble()?.times(novaQtde.text.toDouble())?.div(
            item.quantidade?.toDouble()
                ?: 100.0)) ?: 0.0
        novoProt.value = (item.proteina?.toDouble()?.times(novaQtde.text.toDouble())?.div(
            item.quantidade?.toDouble()
                ?: 100.0)) ?: 0.0
        novoGord.value = (item.lipidios?.toDouble()?.times(novaQtde.text.toDouble())?.div(
            item.quantidade?.toDouble()
                ?: 100.0)) ?: 0.0
    }catch (e: Exception){
        Toast.makeText(context, "Insira um numero valido!", Toast.LENGTH_LONG).show()
    }
    //novaQtde.text = item.quantidade.toString()
    Card(
        modifier = Modifier
            .padding(top = 20.dp , start = 10.dp , end = 8.dp)
            .fillMaxWidth()// adding some space to the label
            //.background(color = DarkBlueTrans2 , shape = RoundedCornerShape(15.dp))
        ,
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp) ,



        ){
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.White)
        ){
        Column {
            Text(
                text = item.alimento ,
                style = MaterialTheme.typography.h6 ,
                color = Color.Black ,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(5.dp))
            Row {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Cal: ${novoCal.value.toFloat()}g" ,
                        style = MaterialTheme.typography.subtitle1 ,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = "C: ${novoCarb.value.toInt()}g" ,
                        style = MaterialTheme.typography.subtitle1 ,
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = "P: ${novoProt.value.toInt()}g" ,
                        style = MaterialTheme.typography.subtitle1 ,
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = "G: ${novoGord.value.toInt()}g" ,
                        style = MaterialTheme.typography.subtitle1 ,
                    )


                }
                Spacer(modifier = Modifier.size(20.dp))
                Row(
                    horizontalArrangement = Arrangement.Center ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(

                        value = novaQtde.text , onValueChange = { novaQtde.text = it } ,
                        modifier = Modifier
                            .width(100.dp)
                        ,
                        label = {
                            Text(text = "GRAMAS")
                        } ,
                        placeholder = {
                            Text(text = "0")
                        }
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Column() {
                        Button(
                            modifier = Modifier
                                //.background(DarkOrange)
                                .padding(5.dp) ,
                            shape = RoundedCornerShape(20.dp) ,
                            onClick = {
                                try {
                                    if(novaQtde.text.isNotBlank()){
                                        item.caloria = novoCal.value.toString()
                                        item.carboidrato = novoCarb.value.toString()
                                        item.proteina = novoProt.value.toString()
                                        item.lipidios = novoGord.value.toString()
                                        item.quantidade = novaQtde.text
                                        onClickAtt(item)
                                    }


                                } catch (e: Exception) {
                                    Toast.makeText(context , " $e" , Toast.LENGTH_LONG).show()
                                }


                            }) {
                            Icon(imageVector = Icons.Default.Check , contentDescription = "add")
                        }
                        Button(
                            modifier = Modifier
                                //.background(DarkOrange)
                                .padding(5.dp) ,
                            shape = RoundedCornerShape(20.dp) ,
                            onClick = {
                                try {
                                    onCancel()

                                } catch (e: Exception) {
                                    Toast.makeText(context , " $e" , Toast.LENGTH_LONG).show()
                                }


                            }) {
                            Icon(imageVector = Icons.Default.Close , contentDescription = "add")
                        }
                    }
                }
            }

        }
    }
    }
}

class TextFieldState(){
    var text: String by mutableStateOf("")
}
