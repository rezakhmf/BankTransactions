package com.farahaniconsulting.banktransactions.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.util.Locale

@Composable
fun getCategoryIcon(category: String): ImageVector {
    return when (category.lowercase(Locale.ROOT)) {
        "shopping" -> Icons.Default.LocalMall
        "business" -> Icons.Default.Work
        "entertainment" -> Icons.Default.Visibility
        "groceries" -> Icons.Default.LocalGroceryStore
        "eatingout" -> Icons.Default.Restaurant
        "transport" -> Icons.Default.Traffic
        "cash" -> Icons.Default.Money
        "uncategorised" -> Icons.Default.Category
        else -> Icons.Default.Category
    }
}

@Composable
fun getPendingText(isPending: Boolean): String? {
    return if (isPending) {
        "Pending"
    } else {
        null
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}