package com.farahaniconsulting.banktransactions.ui.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.ui.common.SetStatusBarColor
import com.farahaniconsulting.banktransactions.ui.common.UIState
import com.farahaniconsulting.banktransactions.ui.component.ShowError
import com.farahaniconsulting.banktransactions.ui.component.ShowLoading
import com.farahaniconsulting.banktransactions.ui.theme.BackgroundGrey
import com.farahaniconsulting.banktransactions.ui.transactions.components.TransactionList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: TransactionsViewModel = hiltViewModel()
) {

    val uiState: UIState<Transactions> by viewModel.transactionsUiSate.collectAsStateWithLifecycle()

    SetStatusBarColor(color = MaterialTheme.colorScheme.onPrimary)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                Text(text = "Complete Access")
             },
            )
        },

    ) { innerPadding ->
        TransactionsContent(
        modifier = modifier
            .padding(innerPadding)
            ,

        navController = navController,
        uiState = uiState
        )
    }


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