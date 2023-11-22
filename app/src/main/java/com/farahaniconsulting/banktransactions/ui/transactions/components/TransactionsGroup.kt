package com.farahaniconsulting.banktransactions.ui.transactions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farahaniconsulting.banktransactions.domain.model.Transaction
import com.farahaniconsulting.banktransactions.util.getDaysDifference

@Composable
fun TransactionsGroup(date: String, transactions: List<Transaction>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Date
        Row {
            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Text(
                text = getDaysDifference(date).toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Column {
            transactions.forEach { transaction ->
                TransactionItem(transaction = transaction)
                // Divider here
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}
