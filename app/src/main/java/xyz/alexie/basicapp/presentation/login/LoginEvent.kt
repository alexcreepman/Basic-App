package xyz.alexie.basicapp.presentation.login

sealed class LoginEvent {
    data class EmailInput(val value: String) : LoginEvent()
    data class PasswordInput(val value: String) : LoginEvent()
    object PasswordVisibilityChange : LoginEvent()
}
