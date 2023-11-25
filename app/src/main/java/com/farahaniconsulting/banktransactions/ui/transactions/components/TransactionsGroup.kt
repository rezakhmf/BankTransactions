package com.farahaniconsulting.banktransactions.ui.transactions.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farahaniconsulting.banktransactions.domain.model.Transaction
import com.farahaniconsulting.banktransactions.util.convertDateFormat
import com.farahaniconsulting.banktransactions.util.getDaysDifference

@Composable
fun TransactionsGroup(date: String, transactions: List<Transaction>) {
    Column {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = convertDateFormat(date),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = getDaysDifference(date).toString(),
                modifier = Modifier
                    .padding(top = 2.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        transactions.forEach { transaction ->
            Spacer(modifier = Modifier.height(16.dp))
            TransactionItem(transaction = transaction)
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                color = MaterialTheme.colorScheme.onTertiary,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}
