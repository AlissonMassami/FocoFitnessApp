package com.focofitness.appexercicios.presentation.Perfil.permissions

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.focofitness.appexercicios.R
import com.focofitness.appexercicios.domain.util.getBitmapFromImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun cameraPermissionState(): PermissionState {
    return rememberPermissionState(permission = CAMERA_PERMISSION)
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun galleryPermissionState(): PermissionState {
    return rememberPermissionState(permission = READ_EXTERNAL_STORAGE)
}



/**
 * Launches the permission only to take a picture.
 * This launcher cannot be reused for other permission launcher type, since the return
 * result will be of type Bitmap.
 */
@Composable
fun imagePermissionLauncher(icon : Bitmap): ManagedActivityResultLauncher<Void?, Bitmap?> {
    val result = remember { mutableStateOf(icon) }

    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let {
            result.value = bitmap
        }
    }

    // Use the result
}



//val placeHolderBitmap: Bitmap = BitmapFactory.decodeResource(
//    Resources.getSystem(),
//    R.mipmap.ic_profile_foreground
//)