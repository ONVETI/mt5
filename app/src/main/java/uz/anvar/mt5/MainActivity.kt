package uz.anvar.mt5

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.anvar.mt5.screens.main.MainRoute
import uz.anvar.mt5.screens.main.mainRoute
import uz.anvar.mt5.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.WHITE,
                Color.WHITE
            )
        )
        setContent {
            AppTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = MainRoute,
                    modifier = Modifier,
//                        .background(color = AppTheme.colors.surfaceBackground1)
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(0),
                        ) + fadeIn(animationSpec = tween(0))
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { -it / 10 },
                            animationSpec = tween(0),
                        ) + fadeOut(animationSpec = tween(0))
                    },
                    popEnterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { -it / 10 },
                            animationSpec = tween(0),
                        ) + fadeIn(animationSpec = tween(0))
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { it },
                            animationSpec = tween(0),
                        ) + fadeOut(animationSpec = tween(0))
                    },
                ) {

                    mainRoute(navController)

                }
            }
        }
    }
}