package com.farahaniconsulting.banktransactions.ui.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farahaniconsulting.banktransactions.domain.model.Transaction
import com.farahaniconsulting.banktransactions.ui.common.getCategoryIcon
import com.farahaniconsulting.banktransactions.ui.common.getPendingText
import com.farahaniconsulting.banktransactions.util.formatMoneyAmount
import com.farahaniconsulting.banktransactions.util.removeHtmlTags
import java.util.Locale

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
           horizontalArrangement = Arrangement.SpaceEvenly,
          verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = getCategoryIcon(transaction.category),
            contentDescription = transaction.category,
            tint =  MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .size(32.dp)
                .padding(top = 4.dp)
                .background(MaterialTheme.colorScheme.onTertiary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        getPendingText(transaction.isPending)?.let { pendingText ->
            Text(
                text = pendingText.uppercase(Locale.ROOT),
                style = MaterialTheme.typography.displayMedium
                    .copy(color = MaterialTheme.colorScheme.onTertiary),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = removeHtmlTags(transaction.description),
            modifier = Modifier
                .padding(top = 4.dp)
                .weight(1f),
            style = MaterialTheme.typography.titleMedium
                .copy(color = MaterialTheme.colorScheme.onTertiary
                )
        )
        Text(
            formatMoneyAmount(transaction.amount),
            modifier = Modifier
                .padding(top = 4.dp, start = 8.dp),
            style =  MaterialTheme.typography.titleMedium,
        )
    }
}

