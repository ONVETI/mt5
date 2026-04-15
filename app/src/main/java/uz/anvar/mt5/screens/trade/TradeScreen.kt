package uz.anvar.mt5.screens.trade

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.trade.component.TradeBottomBar
import uz.anvar.mt5.screens.trade.component.TradeTopBar
import uz.anvar.mt5.screens.trade.state.TradeAction
import uz.anvar.mt5.screens.trade.state.TradeState
import uz.anvar.mt5.ui.theme.AppTheme

@Composable
internal fun TradeScreen(
    state: TradeState,
    onAction: (TradeAction) -> Unit,
    drawerAction: (MainAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            TradeTopBar(
                state = state,
                onAction = onAction,
                drawerAction = drawerAction
            )
        },
        bottomBar = {
            TradeBottomBar(
                state = state,
                onAction = onAction,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        TradeContent(
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

@Composable
internal fun TradeContent(
    state: TradeState,
    onAction: (TradeAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    // Content implementation
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewTradeScreen() {
    AppTheme {
        TradeScreen(
            state = TradeState(),
            onAction = {},
            drawerAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
