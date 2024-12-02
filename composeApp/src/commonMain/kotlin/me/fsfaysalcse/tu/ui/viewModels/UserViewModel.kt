package me.fsfaysalcse.tu.ui.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.fsfaysalcse.tu.data.database.UserDatabase
import me.fsfaysalcse.tu.data.database.UserEntity
import me.fsfaysalcse.tu.ui.uiStates.HomeUiState
import me.fsfaysalcse.tu.ui.uiStates.LoginUiState
import me.fsfaysalcse.tu.ui.uiStates.SignupUiState

class UserViewModel(
    private val userDatabase: UserDatabase
) : ViewModel() {


    init {
        viewModelScope.launch {
            val users = userDatabase.userDao().getAllUsers()
            println(users)
        }
    }


    private var userSignupState_ = mutableStateOf<SignupUiState>(SignupUiState.Empty)
    val userSignupState: State<SignupUiState> = userSignupState_

    fun signup(user: UserEntity) {
        userSignupState_.value = SignupUiState.Empty

        if (user.name.isBlank()) {
            userSignupState_.value = SignupUiState.Error("Name is required.")
            return
        }

        if (user.phone.isBlank() || !user.phone.matches(Regex("^[+]?[0-9]{10,15}\$"))) {
            userSignupState_.value = SignupUiState.Error("A valid phone number is required.")
            return
        }

        if (user.email.isBlank() || !user.email.matches(Regex("^[\\w-.]+@[\\w-]+\\.[a-z]{2,6}\$"))) {
            userSignupState_.value = SignupUiState.Error("A valid email is required.")
            return
        }

        if (user.password.isBlank() || user.password.length < 6) {
            userSignupState_.value =
                SignupUiState.Error("Password must be at least 6 characters long.")
            return
        }


        viewModelScope.launch {
            try {
                userDatabase.userDao().signup(user)
                userSignupState_.value = SignupUiState.Success
            } catch (e: Exception) {
                userSignupState_.value = SignupUiState.Error(e.message ?: "An error occurred.")
            }
        }
    }


    private var userLoginState_ = mutableStateOf<LoginUiState>(LoginUiState.Empty)
    val userLoginState: State<LoginUiState> = userLoginState_

    fun login(email: String, password: String) {
        userLoginState_.value = LoginUiState.Empty

        if (email.isBlank() || !email.matches(Regex("^[\\w-.]+@[\\w-]+\\.[a-z]{2,6}\$"))) {
            userLoginState_.value = LoginUiState.Error("A valid email is required.")
            return
        }

        if (password.isBlank() || password.length < 6) {
            userLoginState_.value =
                LoginUiState.Error("Password must be at least 6 characters long.")
            return
        }

        viewModelScope.launch {
            try {
                val user = userDatabase.userDao().login(email, password)
                if (user != null) {
                    userLoginState_.value = LoginUiState.Success
                } else {
                    userLoginState_.value = LoginUiState.Error("Invalid email or password")
                }
            } catch (e: Exception) {
                userLoginState_.value = LoginUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun clearLoginState() {
        userLoginState_.value = LoginUiState.Empty
    }


    private var homeUiState_ = mutableStateOf<HomeUiState>(HomeUiState.Empty)
    val homeUiState: State<HomeUiState> = homeUiState_



    fun getUserByEmail(email: String) {
        homeUiState_.value = HomeUiState.Empty
        viewModelScope.launch {
            try {
                val user = userDatabase.userDao().getUserByEmail(email)
                homeUiState_.value = HomeUiState.Success(user.toUser())
            } catch (e: Exception) {
                homeUiState_.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun clearHomeState() {
        homeUiState_.value = HomeUiState.Empty
    }

    fun clearDatabase() {
        viewModelScope.launch {
            userDatabase.userDao().clearDatabase()
        }
    }
}