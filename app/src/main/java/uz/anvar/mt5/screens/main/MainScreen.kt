package uz.anvar.mt5.screens.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.anvar.mt5.compose.theme.AppTheme
import uz.anvar.mt5.screens.main.component.MainBottomBar
import uz.anvar.mt5.screens.main.component.MainTopBar
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.main.state.MainState

@Composable
internal fun MainScreen(
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
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

@Composable
internal fun MainContent(
    state: MainState,
    onAction: (MainAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    // Content implementation
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewMainScreen() {
    AppTheme {
        MainScreen(
            state = MainState(),
            onAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
