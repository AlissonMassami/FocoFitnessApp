package com.example.appexercicios.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


val Yellow = Color(0xFFFFF9C7)
val DarkOrange = Color(0xFFFF9F1C)
val DarkOrangeTrans = Color(0xDFFDD46E)
val MedOrange = Color(0xFFFFBF69)
val LightOrange = Color(0xFFFFDFB4)
val LightBlue = Color(0xFFE5F9F8)
val MedBlue = Color(0xFFCBF3F0)
val DarkBlue = Color(0xFF2EC4B6)
val DarkBlue2 = Color(0xFF05CFBC)
val DarkBlueTrans = Color(0x992EC4B6)
val DarkBlueTrans2 = Color(0x6350DBCE)


val Colors.activeIndicatorColor
    get() = if (isLight) Purple500 else Purple700

val Colors.inactiveIndicatorColor
    get() = if (isLight) LightGray else DarkGray

val Colors.buttonBackgroundColor
    get() = if (isLight) Purple500 else Purple700