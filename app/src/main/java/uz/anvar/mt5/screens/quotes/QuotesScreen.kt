package uz.anvar.mt5.screens.quotes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.anvar.mt5.compose.theme.AppTheme
import uz.anvar.mt5.screens.quotes.component.QuotesBottomBar
import uz.anvar.mt5.screens.quotes.component.QuotesTopBar
import uz.anvar.mt5.screens.quotes.state.QuotesAction
import uz.anvar.mt5.screens.quotes.state.QuotesState

@Composable
internal fun QuotesScreen(
    state: QuotesState,
    onAction: (QuotesAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            QuotesTopBar(
                state = state,
                onAction = onAction,
            )
        },
        bottomBar = {
            QuotesBottomBar(
                state = state,
                onAction = onAction,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        QuotesContent(
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

@Composable
internal fun QuotesContent(
    state: QuotesState,
    onAction: (QuotesAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    // Content implementation
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewQuotesScreen() {
    AppTheme {
        QuotesScreen(
            state = QuotesState(),
            onAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
