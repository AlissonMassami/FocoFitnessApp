package com.focofitness.appexercicios.component

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ImagemRedonda (btm: Bitmap, modifier: Modifier, onClick: () -> Unit ,){
    Image(
        bitmap = btm.asImageBitmap() ,
        contentDescription = null ,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable {
                onClick
            }
            .padding(3.dp)
            .border(
                width = 1.dp ,
                color = Color.Black ,
                shape = CircleShape) ,
    )

}