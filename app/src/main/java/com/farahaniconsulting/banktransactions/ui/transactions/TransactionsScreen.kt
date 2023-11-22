package com.farahaniconsulting.banktransactions.ui.transactions

import android.accounts.Account
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.farahaniconsulting.banktransactions.data.model.dto.account.AccountDto
import com.farahaniconsulting.banktransactions.domain.model.Transaction
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.ui.common.UIState
import com.farahaniconsulting.banktransactions.ui.common.getCategoryIcon
import com.farahaniconsulting.banktransactions.ui.common.getPendingText
import com.farahaniconsulting.banktransactions.ui.component.ShowError
import com.farahaniconsulting.banktransactions.ui.component.ShowLoading
import com.farahaniconsulting.banktransactions.ui.theme.BackgroundGrey
import com.farahaniconsulting.banktransactions.ui.theme.typography
import com.farahaniconsulting.banktransactions.util.getDaysDifference


@Composable
fun TransactionsScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: TransactionsViewModel = hiltViewModel()
) {

    val uiState: UIState<Transactions> by viewModel.transactionsUiSate.collectAsStateWithLifecycle()

    TransactionsContent(
        modifier = modifier,
        navController = navController,
        uiState = uiState
    )
}

@Composable
fun TransactionsContent(
    modifier: Modifier = Modifier,
    uiState: UIState<Transactions>,
    navController: NavHostController,
) {
    // Screen Loading state
    if (uiState.isLoading) {
        ShowLoading(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGrey)
        )
    } else if (!uiState.error?.asString(LocalContext.current).isNullOrEmpty()) {
        ShowError(
            error = uiState.error?.asString(LocalContext.current),
            modifier = modifier
                .fillMaxSize()
                .background(BackgroundGrey)
        )
    } else {
        uiState.data?.let {
            TransactionList(it)
        }
    }

}


@Composable
fun TransactionList(transactions: Transactions) {

    val groupedTransactions = transactions.transactions.groupBy { it.effectiveDate }

    // State to hold the header height
    var headerHeight by remember { mutableIntStateOf(0) }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        FixedHeader(transactions = transactions,
            onHeaderHeightMeasured = { height ->
                // Update the header height when measured
                headerHeight = height
            })
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
    }
}

@Composable
fun FixedHeader(transactions: Transactions,
    onHeaderHeightMeasured: (Int) -> Unit) {

    val pendingSum = transactions.transactions.filter { it.isPending }
        .sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
    val account = transactions.account

    // Ref to hold the header height
    val headerRef = rememberUpdatedState(onHeaderHeightMeasured)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            // Attach a callback to measure the height when the composable is first laid out
            .onGloballyPositioned { coordinates ->
                headerRef.value(coordinates.size.height)
            }
    ) {
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
            Divider(color = Color.Gray, thickness = 1.dp)
        }
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

@Composable
fun TransactionItem(transaction: Transaction) {
    // Date and Pending
    Text(
        text = transaction.effectiveDate,
        style = MaterialTheme.typography.headlineMedium,
        color = if (transaction.isPending) Color.Red else Color.Gray
    )
    getPendingText(transaction.isPending)?.let { pendingText ->
        Text(
            text = pendingText,
            style = MaterialTheme.typography.displayLarge,
            color = Color.Red
        )
    }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Category Icon
        Icon(
            imageVector = getCategoryIcon(transaction.category),
            contentDescription = transaction.category
        )
        // Category Name, Description, and Amount
        Text(
            text = "${transaction.category} ${transaction.description} ${transaction.amount}",
            style = MaterialTheme.typography.headlineMedium
        )
    }

}

