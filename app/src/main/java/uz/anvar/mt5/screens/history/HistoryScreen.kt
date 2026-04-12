package uz.anvar.mt5.screens.history

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.anvar.mt5.compose.theme.AppTheme
import uz.anvar.mt5.screens.history.component.HistoryBottomBar
import uz.anvar.mt5.screens.history.component.HistoryTopBar
import uz.anvar.mt5.screens.history.state.HistoryAction
import uz.anvar.mt5.screens.history.state.HistoryState

@Composable
internal fun HistoryScreen(
    state: HistoryState,
    onAction: (HistoryAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            HistoryTopBar(
                state = state,
                onAction = onAction,
            )
        },
        bottomBar = {
            HistoryBottomBar(
                state = state,
                onAction = onAction,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        HistoryContent(
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

@Composable
internal fun HistoryContent(
    state: HistoryState,
    onAction: (HistoryAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    // Content implementation
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewHistoryScreen() {
    AppTheme {
        HistoryScreen(
            state = HistoryState(),
            onAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
