package com.focofitness.appexercicios.presentation.Perfil.permissions

import android.Manifest

const val CAMERA_PERMISSION = Manifest.permission.CAMERA
const val READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE


fun samplePermissions(): List<String> = listOf(
    CAMERA_PERMISSION,
    READ_EXTERNAL_STORAGE
)