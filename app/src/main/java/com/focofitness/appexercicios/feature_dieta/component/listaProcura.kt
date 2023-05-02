@file:Suppress("UNUSED_EXPRESSION")

package com.focofitness.appexercicios.feature_dieta.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica

@Composable
fun ListaProcura(tabelaCalorica: TabelaCalorica , onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable { onClick() }
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
            ) {
            Text(text = tabelaCalorica.alimento,
            style = TextStyle(color = Color.Black),
                maxLines = 2,
                modifier = Modifier.weight(5f),
            )
            Column(horizontalAlignment = Alignment.End,
            modifier = Modifier.weight(5f)) {
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Caloria:" ,
                        style = TextStyle(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    val cal = tabelaCalorica.caloria?.toDouble()?: 0.0
                    Text(
                        text = cal.toInt().toString() ,
                        style = TextStyle(color = Color.Blue),
                    )

                }
                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(text = "C: ")
                    var carb = 0.0
                    if(tabelaCalorica.carboidrato?.isBlank() == false){
                        carb = tabelaCalorica.carboidrato?.toDouble()!!
                    }
                    Text(text = carb.toInt().toString() ,
                        style = TextStyle(color = Color.Red))
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(text = "P: ")
                    var prot = 0.0
                    if(tabelaCalorica.proteina?.isBlank() == false){
                        prot = tabelaCalorica.proteina?.toDouble()!!
                    }

                    Text(text = prot.toInt().toString() ,
                        style = TextStyle(color = Color.Blue))
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(text = "G: ")
                    var gord = 0.0
                    if(tabelaCalorica.lipidios?.isBlank() == false){
                        gord = tabelaCalorica.lipidios?.toDouble()!!
                    }

                    Text(text = "${gord.toInt()}",
                        style = TextStyle(color = Color.Green))
                }
            }

        }
    }

}


@Preview
@Composable
fun previewLista(){
    val tabela = TabelaCalorica(
        alimento = "Arroz Cozido adad adssd sdsd sdsdsdssds ds",
        caloria = "351.12121",
        proteina = "1.237",
        carboidrato = "1.237",
        lipidios = ""
    )
    ListaProcura(tabelaCalorica = tabela ){

    }
}