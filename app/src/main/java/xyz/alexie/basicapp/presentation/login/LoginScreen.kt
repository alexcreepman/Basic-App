@file:JvmName("LoginScreenKt")

package xyz.alexie.basicapp.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
private fun AuthBodyPreview() {
    LoginBody(
        email = "example@cerf7.xyz",
        password = "secure_pwd",
        passwordVisible = false,
        buttonEnabled = true,
        onEmailChange = {},
        onPasswordChange = {},
        onLogin = {},
        onPasswordVisibilityChange = {},
    )
}

@Composable
fun LoginScreen(
    navController: NavController,
) {
    val viewModel: LoginViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    LoginBody(
        email = state.email,
        password = state.password,
        passwordVisible = state.passwordVisible,
        buttonEnabled = state.loginEnabled,
        onEmailChange = { viewModel.onLoginEvent(LoginEvent.EmailInput(it)) },
        onPasswordChange = { viewModel.onLoginEvent(LoginEvent.PasswordInput(it)) },
        onPasswordVisibilityChange = { viewModel.onLoginEvent(LoginEvent.PasswordVisibilityChange) },
        onLogin = { navController.navigate("Restaurants") }
    )
}

@Composable
private fun LoginBody(
    email: String,
    password: String,
    passwordVisible: Boolean,
    buttonEnabled: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .padding(top = 50.dp)
                .width(175.dp)
                .height(140.dp),
            painter = painterResource(id = xyz.alexie.basicapp.R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            text = "Login To Your Account",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            value = email,
            onValueChange = onEmailChange,
            label = {
                Text(
                    text = "Email", style = TextStyle(
                        fontSize = 14.sp, color = Color(0xFF3B3B3B)
                    )
                )
            })
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChange,
            label = {
                Text(
                    text = "Password", style = TextStyle(
                        fontSize = 14.sp, color = Color(0xFF3B3B3B)
                    )
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            trailingIcon = {
                IconButton(onClick = onPasswordVisibilityChange) {
                    Icon(
                        imageVector = when {
                            passwordVisible -> Icons.Filled.Visibility
                            else -> Icons.Filled.VisibilityOff
                        },
                        contentDescription = null
                    )
                }
            },
        )
        Button(
            modifier = Modifier
                .padding(top = 24.dp)
                .width(140.dp)
                .height(60.dp),
            onClick = onLogin,
            enabled = buttonEnabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green
            ),
            content = {
                Text(
                    text = "Login",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
        )
    }
}
