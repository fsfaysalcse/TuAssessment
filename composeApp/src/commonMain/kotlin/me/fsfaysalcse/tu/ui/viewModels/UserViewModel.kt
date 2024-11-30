package me.fsfaysalcse.tu.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.fsfaysalcse.tu.data.database.UserDatabase
import me.fsfaysalcse.tu.data.models.User
import me.fsfaysalcse.tu.ui.uiStates.HomeUiState
import me.fsfaysalcse.tu.ui.uiStates.LoginUiState
import me.fsfaysalcse.tu.ui.uiStates.SignupUiState

class UserViewModel(
    private val userDatabase: UserDatabase
) : ViewModel() {


    var userInfo: MutableState<HomeUiState?> = mutableStateOf(null)
        private set

    init {
        viewModelScope.launch {
            val users = userDatabase.userDao().getAllUsers()
            println(users)
        }
    }


    private var userSignupState_ = mutableStateOf<SignupUiState>(SignupUiState.Empty)
    val userSignupState: State<SignupUiState> = userSignupState_

    fun signup(user: User) {
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
                userDatabase.userDao().signup(user.toUserEntity())
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


    fun getUserByEmail(email: String) {
        userInfo.value = HomeUiState.Empty
        viewModelScope.launch {
            try {
                val user = userDatabase.userDao().getUserByEmail(email)
                userInfo.value = HomeUiState.Success(user.toUser())
            } catch (e: Exception) {
                userInfo.value = null
            }
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            userDatabase.userDao().clearDatabase()
        }
    }
}