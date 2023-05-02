package com.focofitness.appexercicios.component

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun pickImageFromGallery(imageUri: Uri?): MutableState<Bitmap?> {


    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val bitmapNull = remember { mutableStateOf<Bitmap?>(null) }

        if (imageUri != null){
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, imageUri)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver , imageUri!!)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
        }
    return bitmap

}