package com.farahaniconsulting.banktransactions.ui.transactions.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.farahaniconsulting.banktransactions.domain.model.Transactions

@Composable
fun TransactionList(
    modifier: Modifier,
    transactions: Transactions
) {

    val groupedTransactions = transactions.transactions.groupBy { it.effectiveDate }

    var headerHeight by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {
        LazyColumn(
            modifier = Modifier
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

