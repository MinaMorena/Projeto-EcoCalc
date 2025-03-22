package br.com.fiap.ecocalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.fiap.ecocalc.screens.LoginScreen
import br.com.fiap.ecocalc.screens.MenuScreen
import br.com.fiap.ecocalc.screens.CarroScreen
import br.com.fiap.ecocalc.screens.AereoScreen
import br.com.fiap.ecocalc.screens.HistoricoScreen
import br.com.fiap.ecocalc.screens.PerfilScreen
import br.com.fiap.ecocalc.ui.theme.EcoCalcTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.composable

//URL https://www.carboninterface.com/api/v1/estimates
//API key Bearer lLgr41Eg1C2d46YFXsOV2Q  que vai no Bearer Token, o bearer é o prefix
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoCalcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = "login",
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentScope.SlideDirection.End,
                                animationSpec = tween(2000)) + fadeOut(animationSpec = tween(1000))
                        },
                        enterTransition = {
                            slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Start, animationSpec = tween(1000))
                        }
                    ) {
                        composable(route = "login") { LoginScreen(navController) }
                        composable(route = "menu") { MenuScreen(navController) }
                        composable(route = "carros") { CarroScreen(navController) }
                        composable(route = "aereos") { AereoScreen(navController) }
                        composable(route = "perfil") { PerfilScreen(navController) }

                    }
                }
            }
        }
    }
}
