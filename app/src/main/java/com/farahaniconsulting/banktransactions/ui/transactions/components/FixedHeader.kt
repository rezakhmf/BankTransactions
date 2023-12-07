package com.farahaniconsulting.banktransactions.ui.transactions.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.onGloballyPositioned
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.ui.common.Dimes.Medium
import com.farahaniconsulting.banktransactions.ui.common.Dimes.OnePoint
import com.farahaniconsulting.banktransactions.ui.common.Dimes.Small
import com.farahaniconsulting.banktransactions.ui.common.Dimes.XMedium
import com.farahaniconsulting.banktransactions.ui.common.Dimes.XSmall
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
            thickness = XSmall,
            modifier = Modifier
                .fillMaxWidth()
        .padding(top = Medium)
        )

        Spacer(
            modifier = Modifier
                .padding(top = Medium)
        )

        Column(
            modifier = Modifier
            .padding(start =  XMedium)
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
            Spacer(modifier = Modifier.height(XMedium))
            Row {
                Text(
                    text = "Balance",
                    style = MaterialTheme.typography.displaySmall
                        .copy(color = MaterialTheme.colorScheme.onTertiary)
                )
                Spacer(modifier = Modifier.width(XMedium))
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
                Spacer(modifier = Modifier.width(XMedium))
                Text(
                    formatMoneyAmount(pendingSum.toString()),
                    style = MaterialTheme.typography.displaySmall
                )
            }

            Spacer(modifier = Modifier.height(Medium))
        }

        Divider(color =  MaterialTheme.colorScheme.onTertiary,
            thickness = OnePoint)
        Spacer(modifier = Modifier.height(XMedium))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = XMedium)
        ) {
            Text(
                text = "BSB",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(Small))
            Text( text = account.bsb,
                style = MaterialTheme.typography.titleMedium
                    .copy(color = MaterialTheme.colorScheme.onTertiary)
            )
            Spacer(modifier = Modifier.width(XMedium))
            Text(
                text = "Account",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(Small))
            Text(
                text = splitStringIntoGroups(account.accountNumber),
                style = MaterialTheme.typography.titleMedium
                    .copy(color = MaterialTheme.colorScheme.onTertiary)
            )
        }
        Spacer(modifier = Modifier.height(XMedium))
        Divider(color =  MaterialTheme.colorScheme.onTertiary,
            thickness = OnePoint)
}
}

