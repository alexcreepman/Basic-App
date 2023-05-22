package xyz.alexie.basicapp.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val EMAIL_ADDRESS_PATTERN =
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailInput -> onEmailInput(event.value)
            is LoginEvent.PasswordInput -> onPasswordInput(event.value)
            LoginEvent.PasswordVisibilityChange -> onPasswordVisibilityChange()
        }
    }

    private fun onPasswordVisibilityChange() {
        _state.update {
            it.copy(
                passwordVisible = !it.passwordVisible
            )
        }
    }

    private fun onEmailInput(value: String) {
        _state.update {
            it.copy(
                email = value
            )
        }
        validateInput()
    }

    private fun onPasswordInput(value: String) {
        _state.update {
            it.copy(
                password = value
            )
        }
        validateInput()
    }

    private fun validateInput() {
        val passwordCorrect = state.value.password.validateAsPassword()
        val emailCorrect = state.value.email.validateAsEmail()
        _state.update {
            it.copy(
                loginEnabled = passwordCorrect && emailCorrect
            )
        }
    }

    private fun String.validateAsPassword() =
        this.length > 6 && this.isNotBlank()

    private fun String.validateAsEmail() =
        this.isNotBlank() && this.matches(regex = Regex(EMAIL_ADDRESS_PATTERN))
}