package me.fsfaysalcse.tu.ui.uiStates

sealed class LoginUiState {
    data object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
    data object Empty : LoginUiState()
}

