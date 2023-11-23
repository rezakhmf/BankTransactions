package com.farahaniconsulting.banktransactions.ui.transactions

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.domain.usecase.transaction.TransactionUseCase
import com.farahaniconsulting.banktransactions.ui.common.UIState
import com.farahaniconsulting.banktransactions.util.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val transactionUseCase: TransactionUseCase
) : ViewModel() {

    private var _transactionsUiSate: MutableStateFlow<UIState<Transactions>> =
        MutableStateFlow(UIState(isLoading = true))

    val transactionsUiSate: StateFlow<UIState<Transactions>> =
        _transactionsUiSate.asStateFlow().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState(isLoading = true)
        )

    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing : State<Boolean> = _isRefreshing

    init {
        viewModelScope.launch {
            fetchTransactions()
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(2000)
            fetchTransactions()
            _isRefreshing.value = false
        }
    }

    private suspend fun fetchTransactions() {
        when (val result = transactionUseCase.getTransactions()) {
            is ResultData.Success -> _transactionsUiSate.value =
                UIState(data = result.data)

            is ResultData.Loading -> _transactionsUiSate.value =
                UIState(isLoading = true)

            is ResultData.DoNothing -> {}
        }
    }
}