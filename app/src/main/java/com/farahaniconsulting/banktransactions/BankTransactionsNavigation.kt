package com.farahaniconsulting.banktransactions

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.farahaniconsulting.banktransactions.ui.transactions.TransactionsScreen

@Composable
fun BankTransactionsNavigation(
    appState: TransactionsAppState = rememberTransactionsAppState()
) {
    if (appState.isOnline) {
        NavHost(
            navController = appState.navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) { navBackStackEntry ->
                TransactionsScreen()
            }
        }
    } else {
        OfflineDialog {
            appState.refreshOnline()
        }
    }
}

@Composable
fun OfflineDialog(
    onRetry: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.connection_error_title)) },
        text = { Text(text = stringResource(R.string.connection_error_message)) },
        confirmButton = {
            TextButton(
                onClick = onRetry
            ) {
                Text(text = stringResource(R.string.retry_label))
            }
        })
}