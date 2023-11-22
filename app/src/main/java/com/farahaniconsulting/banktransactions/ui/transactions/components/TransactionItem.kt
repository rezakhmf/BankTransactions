package com.farahaniconsulting.banktransactions.ui.transactions.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farahaniconsulting.banktransactions.domain.model.Transaction
import com.farahaniconsulting.banktransactions.ui.common.getCategoryIcon
import com.farahaniconsulting.banktransactions.ui.common.getPendingText

@Composable
fun TransactionItem(transaction: Transaction) {
    // Date and Pending
    Text(
        text = transaction.effectiveDate,
        style = MaterialTheme.typography.headlineMedium,
        color = if (transaction.isPending) Color.Red else Color.Gray
    )
    getPendingText(transaction.isPending)?.let { pendingText ->
        Text(
            text = pendingText,
            style = MaterialTheme.typography.displayLarge,
            color = Color.Red
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Category Icon
        Icon(
            imageVector = getCategoryIcon(transaction.category),
            contentDescription = transaction.category
        )
        // Category Name, Description, and Amount
        Text(
            text = "${transaction.category} ${transaction.description} ${transaction.amount}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

