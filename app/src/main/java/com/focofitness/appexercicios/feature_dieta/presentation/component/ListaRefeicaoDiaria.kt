package com.focofitness.appexercicios.feature_dieta.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica

@Composable
fun ListaRefeicaoDiaria(
    item: TabelaCalorica,
    onClickEditRefeicao: () -> Unit,
    onClickDelRefeicao: () -> Unit
){

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
        Row(modifier = Modifier.weight(7f)) {
            Column() {
                Row {
                    Text(text = item.alimento,
                    fontWeight = FontWeight.Bold)
                    Text(text ="- Qte: ${item.quantidade}g")
                }
                if(item.caloria?.isBlank() == true){
                    item.caloria = "0.0"
                }
                if(item.carboidrato?.isBlank() == true){
                    item.carboidrato = "0.0"
                }
                if(item.proteina?.isBlank() == true){
                    item.proteina = "0.0"
                }
                if(item.lipidios?.isBlank() == true){
                    item.lipidios = "0.0"
                }
                Text(text = "${item.caloria?.toFloat()?.toInt()}kcal " +
                        "C:${item.carboidrato?.toFloat()?.toInt()} " +
                        "P:${item.proteina?.toFloat()?.toInt()}" +
                        " G:${item.lipidios?.toFloat()?.toInt()}")
            }

        }
        Row(modifier = Modifier.weight(3f)) {
            IconButton(onClick = { onClickEditRefeicao() }) {
                Icon(imageVector = Icons.Filled.Edit , contentDescription = "botaoEdit")
            }
            IconButton(onClick = { onClickDelRefeicao()}) {
                Icon(imageVector = Icons.Filled.Delete , contentDescription = "botaoDel")
            }
        }
    }
}
@Preview
@Composable
fun previewLista(){
    val item = TabelaCalorica(
        alimento = "Arroz Cozido",
        carboidrato = "12.0",
        caloria = "235.12",
        lipidios = "",
        proteina = "16.2"
    )
    ListaRefeicaoDiaria(item = item ,onClickEditRefeicao = { /*TODO*/ }) {
        
    }
}