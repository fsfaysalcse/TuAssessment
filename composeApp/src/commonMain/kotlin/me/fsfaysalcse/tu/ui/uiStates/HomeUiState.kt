package me.fsfaysalcse.tu.ui.uiStates

import me.fsfaysalcse.tu.data.models.User

sealed class HomeUiState {
    data class Success(val user: User) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    data object Empty : HomeUiState()
}