package com.farahaniconsulting.banktransactions.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


val Black = Color(0xFF0E0D0D)
val White = Color(0xFFF3EDEF)
val Grey = Color(0xFFF7F7F7)
val Outline = Color(0xFFE8E8E8)
val Red300 = Color(0xFFB42525)

val LightColorScheme = lightColorScheme(
    primary = Grey,
    onPrimary = Black,
    secondary = Grey,
    onSecondary = White,
    outline = Outline,
    error = Red300,
    onError = Color.White
)

val DarkColorScheme = darkColorScheme(
    primary = Grey,
    onPrimary = White,
    secondary = Grey,
    onSecondary = White,
    outline = Outline,
    error = Red300,
    onError = Color.Black
)