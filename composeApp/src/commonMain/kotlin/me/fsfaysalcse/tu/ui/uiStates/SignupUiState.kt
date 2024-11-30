package me.fsfaysalcse.tu.ui.uiStates

sealed class SignupUiState {
    data object Success : SignupUiState()
    data class Error(val message: String) : SignupUiState()
    data object Empty : SignupUiState()
}
