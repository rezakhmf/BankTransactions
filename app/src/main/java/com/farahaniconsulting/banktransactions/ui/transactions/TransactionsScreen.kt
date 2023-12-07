package com.farahaniconsulting.banktransactions.ui.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.ui.common.Dimes.Small
import com.farahaniconsulting.banktransactions.ui.common.UIState
import com.farahaniconsulting.banktransactions.ui.component.ShowError
import com.farahaniconsulting.banktransactions.ui.component.ShowLoading
import com.farahaniconsulting.banktransactions.ui.theme.BackgroundGrey
import com.farahaniconsulting.banktransactions.ui.transactions.components.TransactionList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: TransactionsViewModel = hiltViewModel()
) {

    val uiState: UIState<Transactions> by viewModel.transactionsUiSate.collectAsStateWithLifecycle()

    val refreshState: State<Boolean> = viewModel.isRefreshing

    TransactionsContent(
        modifier = modifier,
        navController = navController,
        uiState = uiState,
        refreshState = refreshState,
        onSwipeRefresh = viewModel::refreshData

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsContent(
    modifier: Modifier = Modifier,
    uiState: UIState<Transactions>,
    refreshState: State<Boolean>,
    navController: NavHostController,
    onSwipeRefresh: () -> Unit
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

        val topAppBarColors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        )

        uiState.data?.let {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = it.account.accountName,
                                modifier = Modifier
                                    .padding(bottom = Small),
                            )
                        }, colors = topAppBarColors
                    )
                },
            ) { innerPadding ->
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = refreshState.value),
                    onRefresh = { onSwipeRefresh() }) {
                    TransactionList(
                        modifier = modifier
                            .padding(vertical = innerPadding.calculateTopPadding() / 3), it
                    )
                }
            }
        }
    }
}
