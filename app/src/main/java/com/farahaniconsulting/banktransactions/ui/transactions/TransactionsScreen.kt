package com.farahaniconsulting.banktransactions.ui.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.farahaniconsulting.banktransactions.domain.model.Transaction
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.ui.common.UIState
import com.farahaniconsulting.banktransactions.ui.component.ShowError
import com.farahaniconsulting.banktransactions.ui.component.ShowLoading
import com.farahaniconsulting.banktransactions.ui.theme.BackgroundGrey


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
    LazyColumn {
        items(transactions.transactions) { transaction ->
            TransactionItem(transaction = transaction)
            Divider()
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Amount: ${transaction.amount}")
        Text(text = "Category: ${transaction.category}")
        Text(text = "Description: ${transaction.description}")
        Text(text = "Date: ${transaction.effectiveDate}")
        Text(text = "ID: ${transaction.id}")
        Text(text = "Pending: ${transaction.isPending}")
    }
}
