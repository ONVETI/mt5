package uz.anvar.mt5.screens.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.anvar.mt5.screens.charts.ChartsRoute
import uz.anvar.mt5.screens.charts.chartsRoute
import uz.anvar.mt5.screens.history.historyRoute
import uz.anvar.mt5.screens.main.component.MainBottomBar
import uz.anvar.mt5.screens.main.component.MainTopBar
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.main.state.MainState
import uz.anvar.mt5.screens.messages.messagesRoute
import uz.anvar.mt5.screens.quotes.quotesRoute
import uz.anvar.mt5.screens.trade.tradeRoute
import uz.anvar.mt5.ui.theme.AppTheme

@Composable
internal fun MainScreen(
    rootNavController: NavController,
    state: MainState,
    onAction: (MainAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            MainTopBar(
                state = state,
                onAction = onAction,
            )
        },
        bottomBar = {
            MainBottomBar(
                state = state,
                onAction = onAction,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        MainContent(
            rootNavController = rootNavController,
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

@Composable
internal fun MainContent(
    rootNavController: NavController,
    state: MainState,
    onAction: (MainAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ChartsRoute,
        modifier = modifier.padding(paddingValues),
    ) {
        quotesRoute(rootNavController)
        chartsRoute(rootNavController)
        tradeRoute(rootNavController)
        historyRoute(rootNavController)
        messagesRoute(rootNavController)
    }

    LaunchedEffect(state.selectedBottomNavItem) {
        navController.navigate(state.selectedBottomNavItem) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewMainScreen() {
    AppTheme {
        MainScreen(
            state = MainState(selectedBottomNavItem = ChartsRoute),
            onAction = {},
            snackbarHostState = SnackbarHostState(),
            rootNavController = rememberNavController()
        )
    }
}
