package com.farahaniconsulting.banktransactions.ui.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farahaniconsulting.banktransactions.domain.model.Transaction
import com.farahaniconsulting.banktransactions.ui.common.getCategoryIcon
import com.farahaniconsulting.banktransactions.ui.common.makeTransactionInfo
import com.farahaniconsulting.banktransactions.util.formatMoneyAmount

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp),
           horizontalArrangement = Arrangement.SpaceEvenly,
          verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = getCategoryIcon(transaction.category),
            contentDescription = transaction.category,
            tint =  MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(36.dp)
                .padding(top = 4.dp)
                .background(MaterialTheme.colorScheme.onTertiary, CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Row(modifier = Modifier.weight(1f)) {
            Text(
                text = makeTransactionInfo(transaction.isPending,
                        transaction.description),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .weight(1f),
            )
        }
        Text(
            formatMoneyAmount(transaction.amount),
            modifier = Modifier
                .padding(top = 4.dp, start = 8.dp),
            style =  MaterialTheme.typography.titleMedium,
        )
    }
}

