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
import com.farahaniconsulting.banktransactions.ui.common.Dimes.Small
import com.farahaniconsulting.banktransactions.ui.common.Dimes.XMedium
import com.farahaniconsulting.banktransactions.ui.common.Dimes.XSmall
import com.farahaniconsulting.banktransactions.ui.common.Dimes.XxMedium
import com.farahaniconsulting.banktransactions.ui.common.getCategoryIcon
import com.farahaniconsulting.banktransactions.ui.common.makeTransactionInfo
import com.farahaniconsulting.banktransactions.util.formatMoneyAmount

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .padding(start = XMedium, end = XMedium),
           horizontalArrangement = Arrangement.SpaceEvenly,
          verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = getCategoryIcon(transaction.category),
            contentDescription = transaction.category,
            tint =  MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(XxMedium)
                .padding(top = XSmall)
                .background(MaterialTheme.colorScheme.onTertiary, CircleShape)
        )
        Spacer(modifier = Modifier.width(XMedium))
        Row(modifier = Modifier.weight(1f)) {
            Text(
                text = makeTransactionInfo(transaction.isPending,
                        transaction.description),
                modifier = Modifier
                    .padding(top = XSmall)
                    .weight(1f),
            )
        }
        Text(
            formatMoneyAmount(transaction.amount),
            modifier = Modifier
                .padding(top = XSmall, start = Small),
            style =  MaterialTheme.typography.titleMedium,
        )
    }
}

