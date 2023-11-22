package com.farahaniconsulting.banktransactions.ui.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun FixedHeader(
    transactions: Transactions,
    onHeaderHeightMeasured: (Int) -> Unit
) {

    val pendingSum = transactions.transactions.filter { it.isPending }
        .sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
    val account = transactions.account

    val headerRef = rememberUpdatedState(onHeaderHeightMeasured)
    Spacer(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth()
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 16.dp)
            .onGloballyPositioned { coordinates ->
                headerRef.value(coordinates.size.height)
            }
    ) {
            Divider(
                color = MaterialTheme.colorScheme.secondary,
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
        Column(
            modifier = Modifier
                .padding(8.dp)
        ){
            Column {
                Text("Available")
                Text(account.available, style = MaterialTheme.typography.headlineMedium)
            }
            Column {
                Text("Balance")
                Text(account.balance, style = MaterialTheme.typography.headlineMedium)
            }
            Column {
                Text("Pending")
                Text(pendingSum.toString(), style = MaterialTheme.typography.headlineMedium)
            }
        }

        Divider(color = Color.Gray, thickness = 1.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("BSB")
            Text(account.bsb)
            Text("Account")
            Text(account.accountNumber)
        }
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

