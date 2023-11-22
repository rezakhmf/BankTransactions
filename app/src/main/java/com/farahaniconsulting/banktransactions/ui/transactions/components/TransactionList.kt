package com.farahaniconsulting.banktransactions.ui.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.ui.theme.balck20

@Composable
fun TransactionList(transactions: Transactions) {

    val groupedTransactions = transactions.transactions.groupBy { it.effectiveDate }

    // State to hold the header height
    var headerHeight by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 48.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = with(LocalDensity.current) { headerHeight.toDp() })
        ) {
            groupedTransactions.forEach { (date, transactionsGroupedByDate) ->
                item {
                    TransactionsGroup(date, transactions = transactionsGroupedByDate)
                }
            }
        }
        FixedHeader(transactions = transactions,
            onHeaderHeightMeasured = { height ->
                headerHeight = height
            })
    }
}

