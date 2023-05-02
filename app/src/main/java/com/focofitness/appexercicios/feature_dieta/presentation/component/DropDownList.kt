package com.focofitness.appexercicios.feature_dieta.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.focofitness.appexercicios.feature_dieta.component.ListaProcura
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import com.focofitness.appexercicios.ui.theme.DarkOrangeTrans

@Composable
fun DropdownList(
    colorSelected: Color = DarkOrangeTrans ,
    colorBackground: Color = Color.White ,
    expanded: Boolean ,
    selectedIndex: Int ,
    items: State<List<TabelaCalorica>> ,
    onSelect: (item : TabelaCalorica, index: Int) -> Unit ,
    onDismissRequest: () -> Unit ,
    content: @Composable () -> Unit
) {
    Box {
        content()
        androidx.compose.material.DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .background(
                    color = colorBackground ,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            items.value.forEachIndexed { index, s ->
                if (selectedIndex == index) {
                    DropdownMenuItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorSelected ,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        onClick = {  }
                    ) {
                        ListaProcura(tabelaCalorica = s) {
                            onSelect(s, index)
                        }
                    }
                } else {
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {  }
                    ) {
                        ListaProcura(tabelaCalorica = s) {
                            onSelect(s, index)
                        }
                    }
                }
            }
        }
    }
}