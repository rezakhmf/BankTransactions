package com.farahaniconsulting.banktransactions.ui.common

import com.farahaniconsulting.banktransactions.ui.common.UiText.UiText

data class UIState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: UiText? = null
)