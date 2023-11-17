package com.farahaniconsulting.banktransactions.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Yellow800 = Color(0x4FFFEB3B)
var Red300 = Color(0xFF009688)

val grey = Color(0xFFBBB9B1)
var orange = Color(0xFFFFC107)

val LightColorScheme = lightColorScheme(
    primary = Yellow800,
    onPrimary = Color.White,
    inversePrimary = Yellow800,
    secondary = Color.Black,
    onSecondary = Color.Black,
    error = Red300,
    onError = Color.White
)

val DarkColorScheme = darkColorScheme(
    primary = grey,
    onPrimary = orange,
    inversePrimary = Yellow800,
    secondary = Color.Black,
    onSecondary = Color.Black,
    error = Red300,
    onError = Color.Black
)