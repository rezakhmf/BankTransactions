package com.farahaniconsulting.banktransactions.ui.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.util.formatMoneyAmount
import com.farahaniconsulting.banktransactions.util.splitStringIntoGroups

@Composable
fun FixedHeader(
    transactions: Transactions,
    onHeaderHeightMeasured: (Int) -> Unit
) {

    val pendingSum = transactions.transactions.filter { it.isPending }
        .sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
    val account = transactions.account

    val headerRef = rememberUpdatedState(onHeaderHeightMeasured)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .onGloballyPositioned { coordinates ->
                headerRef.value(coordinates.size.height)
            }
    ) {
        Divider(
            color = MaterialTheme.colorScheme.outline,
            thickness = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )

        Spacer(
            modifier = Modifier
                .padding(top = 32.dp)
        )

        Column(
            modifier = Modifier
            .padding(start =  16.dp)
        ) {
            Column {
                Text(
                    text = "Available",
                    style = MaterialTheme.typography.displaySmall,
                    )
                Text(
                    formatMoneyAmount(account.available),
                    style = MaterialTheme.typography.displayLarge
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = "Balance",
                    style = MaterialTheme.typography.displaySmall
                        .copy(color = MaterialTheme.colorScheme.onTertiary)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = formatMoneyAmount(account.balance),
                    style = MaterialTheme.typography.displaySmall
                )
            }
            Row {
                Text(
                    text = "Pending",
                    style = MaterialTheme.typography.displaySmall
                        .copy(color = MaterialTheme.colorScheme.onTertiary)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    formatMoneyAmount(pendingSum.toString()),
                    style = MaterialTheme.typography.displaySmall
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                text = "BSB",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text( text = account.bsb,
                style = MaterialTheme.typography.titleMedium
                    .copy(color = MaterialTheme.colorScheme.onTertiary)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Account",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = splitStringIntoGroups(account.accountNumber),
                style = MaterialTheme.typography.titleMedium
                    .copy(color = MaterialTheme.colorScheme.onTertiary)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

