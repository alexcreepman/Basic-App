package xyz.alexie.basicapp.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val loginEnabled: Boolean = false,
)