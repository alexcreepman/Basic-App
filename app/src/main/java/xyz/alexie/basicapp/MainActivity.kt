package xyz.alexie.basicapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import xyz.alexie.basicapp.presentation.login.LoginScreen
import xyz.alexie.basicapp.presentation.restaraunts.RestaurantDetail
import xyz.alexie.basicapp.presentation.restaraunts.RestaurantsScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContent {
            Surface() {
                val navController = rememberNavController()
                NavHost(
                    modifier = Modifier,
                    navController = navController,
                    startDestination = "Login"
                ) {
                    composable("Login") {
                        LoginScreen(navController)
                    }
                    composable("Restaurants") {
                        RestaurantsScreen(navController)
                    }
                    composable(
                        "RestaurantDetail/{name}/{deliveryTime}/{imagePath}",
                        arguments = listOf(
                            navArgument("name") { type = NavType.StringType },
                            navArgument("deliveryTime") { type = NavType.StringType },
                            navArgument("imagePath") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        RestaurantDetail(
                            name = backStackEntry.arguments?.getString("name")!!,
                            deliveryTime = backStackEntry.arguments?.getString("deliveryTime")!!,
                            imagePath = backStackEntry.arguments?.getString("imagePath")!!
                        )
                    }
                }
            }
        }
        super.onCreate(savedInstanceState)
    }
}