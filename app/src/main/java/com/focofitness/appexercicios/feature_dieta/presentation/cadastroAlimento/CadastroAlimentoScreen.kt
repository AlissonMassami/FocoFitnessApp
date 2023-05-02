package com.focofitness.appexercicios.feature_dieta.presentation.cadastroAlimento

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.focofitness.appexercicios.component.TopBar
import com.focofitness.appexercicios.feature_dieta.component.cadastroAlimentos
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.feature_dieta.presentation.DietaScreen.SearchTopBar
import com.focofitness.appexercicios.feature_dieta.presentation.DietaScreen.TextFieldState
import com.focofitness.appexercicios.feature_dieta.presentation.component.DropdownList
import com.focofitness.appexercicios.ui.theme.LightBlue
import com.focofitness.appexercicios.util.TestTags
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalCoilApi
@Composable
fun CadastroAlimentoScreen(
    navController: NavController,
    cadastroAlimentoViewModel: CadastroAlimentoViewModel = hiltViewModel(),
) {
    var searchQuery by cadastroAlimentoViewModel.searchQuery
    val alimento = cadastroAlimentoViewModel.searchedAlimento.collectAsState(emptyList())
    var alimentoSelecionado by cadastroAlimentoViewModel.alimentoSelecionado
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

    val qtdeCal = remember {
        mutableStateOf(0.0)
    }
    val qtdeCarb = remember {
        mutableStateOf(0.0)
    }
    val qtdeProt = remember {
        mutableStateOf(0.0)
    }
    val qtdeGord = remember {
        mutableStateOf(0.0)
    }
    var alimentoEditar = remember {
        mutableStateOf<TabelaCalorica?>(null)
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }
    val context = LocalContext.current


    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            if(!procurarState.value) {
                TopBar(
                    iconeNavegacao = Icons.Filled.ArrowBack ,
                    tituloTopBar = "Novo Alimento" ,
                    onclickVoltar = {
                        navController.popBackStack()
                    } ,
                    iconeEditavel = Icons.Filled.Search ,
                    onClickAcao = {
                        procurarState.value = true
                    }
                )
            } else {
                SearchTopBar(
                    text = searchQuery ,
                    onTextChange = {
                        cadastroAlimentoViewModel.updateSearchQuery(query = it)

                    } ,
                    onSearchClicked = {
                        cadastroAlimentoViewModel.searchAlimento(query = it)
                        mostrarLista.value = true
                        expanded = true
                    } ,
                    onCloseClicked = {
                        procurarState.value = false
                    }
                )
            }


        }) {
        Surface(
            Modifier
                .background(LightBlue)
                .fillMaxSize() ,
            color = LightBlue
        ) {


            Column(verticalArrangement = Arrangement.Top) {
                DropdownList(

                    expanded = expanded ,
                    selectedIndex = selectedIndex ,
                    items = alimento ,
                    onSelect = { alimento , index ->
                        selectedIndex = index

                        cadastroAlimentoViewModel.updateAliementoSelecionado(alimento)
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

            telaCadastro(context){item ->
                cadastroAlimentoViewModel.inserirAlimento(item)
            }
        }
    }
}




@Composable
fun telaCadastro(
    context: Context,
    onClick: (item: TabelaCalorica) -> Unit
) {

    val inputvalueNome = remember { (TextFieldState()) }
    val inputvalueQtde = remember { (TextFieldState()) }
    val inputvalueCal = remember { TextFieldState() }
    val inputvalueCarb = remember { TextFieldState() }
    val inputvalueProt = remember { TextFieldState() }
    val inputvalueGord = remember { TextFieldState() }

    var text = inputvalueNome.text

    Surface(
        Modifier
            .background(LightBlue)
            .fillMaxSize() ,
        color = LightBlue
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column(horizontalAlignment = Alignment.Start) {
            Row(
                verticalAlignment = Alignment.Bottom ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "Nome ")
                TextField(
                    value = inputvalueNome.text ,
                    onValueChange = { inputvalueNome.text = it } ,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent ,
                        focusedIndicatorColor = Color.Transparent ,
                        unfocusedIndicatorColor = Color.Transparent
                    ) ,
                    shape = CutCornerShape(14.dp) ,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    maxLines = 1
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp , end = 10.dp))
            Row(
                verticalAlignment = Alignment.Bottom ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "Quantidade (g/un) ")
                TextField(
                    value = inputvalueQtde.text ,
                    onValueChange = { inputvalueQtde.text = it } ,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent ,
                        focusedIndicatorColor = Color.Transparent ,
                        unfocusedIndicatorColor = Color.Transparent
                    ) ,
                    shape = CutCornerShape(14.dp) ,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    maxLines = 1
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp , end = 10.dp))
            Row(
                verticalAlignment = Alignment.Bottom ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "Calorias ")
                TextField(
                    value = inputvalueCal.text ,
                    onValueChange = { inputvalueCal.text = it } ,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent ,
                        focusedIndicatorColor = Color.Transparent ,
                        unfocusedIndicatorColor = Color.Transparent
                    ) ,
                    shape = CutCornerShape(14.dp) ,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    maxLines = 1
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp , end = 10.dp))
            Row(
                verticalAlignment = Alignment.Bottom ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "Proteínas ")
                TextField(
                    value = inputvalueProt.text ,
                    onValueChange = { inputvalueProt.text = it } ,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent ,
                        focusedIndicatorColor = Color.Transparent ,
                        unfocusedIndicatorColor = Color.Transparent
                    ) ,
                    shape = CutCornerShape(14.dp) ,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    maxLines = 1
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp , end = 10.dp))
            Row(
                verticalAlignment = Alignment.Bottom ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "Carboidratos ")
                TextField(
                    value = inputvalueCarb.text ,
                    onValueChange = { inputvalueCarb.text = it } ,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent ,
                        focusedIndicatorColor = Color.Transparent ,
                        unfocusedIndicatorColor = Color.Transparent
                    ) ,
                    shape = CutCornerShape(14.dp) ,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    maxLines = 1
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp , end = 10.dp))
            Row(
                verticalAlignment = Alignment.Bottom ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "Gorduras ")
                TextField(
                    value = inputvalueGord.text ,
                    onValueChange = { inputvalueGord.text = it } ,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent ,
                        focusedIndicatorColor = Color.Transparent ,
                        unfocusedIndicatorColor = Color.Transparent
                    ) ,
                    shape = CutCornerShape(14.dp) ,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    maxLines = 1
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp , end = 10.dp))

        }
            Button(onClick = {
                try {
                    val convertQtde = inputvalueQtde.text.toInt()
                    val item = TabelaCalorica(
                        alimento = inputvalueNome.text ,
                        quantidade = convertQtde.toString(),
                        caloria = inputvalueCal.text.toDouble().toString(),
                        carboidrato = inputvalueCarb.text.toDouble().toString(),
                        proteina = inputvalueProt.text.toDouble().toString(),
                        lipidios = inputvalueGord.text.toDouble().toString()
                    )
                    onClick(item)
                }catch (e: Exception){
                    Toast.makeText(context, "Insira os dados corretamente", Toast.LENGTH_LONG).show()
                    Log.e(TAG , "erro: $e" , )
                }
            }) {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(imageVector = Icons.Filled.Favorite , contentDescription = null)
                    Text(text = "SALVAR")
                }
            }
        }

    }


}

