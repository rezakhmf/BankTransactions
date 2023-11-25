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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.farahaniconsulting.banktransactions.ui.theme.Montserrat
import com.farahaniconsulting.banktransactions.util.removeHtmlTags
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
fun makeTransactionInfo(isPending: Boolean, description: String) =
    buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 20.sp
            )
        ) {
            if (isPending) {
                append("Pending: ".uppercase(Locale.ROOT))
            }
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 18.sp
            )
        ) {
            append(removeHtmlTags(description))
        }
    }