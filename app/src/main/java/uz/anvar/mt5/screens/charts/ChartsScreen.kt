package uz.anvar.mt5.screens.charts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.anvar.mt5.ui.theme.AppTheme
import uz.anvar.mt5.screens.charts.component.ChartsBottomBar
import uz.anvar.mt5.screens.charts.component.ChartsTopBar
import uz.anvar.mt5.screens.charts.state.ChartsAction
import uz.anvar.mt5.screens.charts.state.ChartsState

@Composable
internal fun ChartsScreen(
    state: ChartsState,
    onAction: (ChartsAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            ChartsTopBar(
                state = state,
                onAction = onAction,
            )
        },
        bottomBar = {
            ChartsBottomBar(
                state = state,
                onAction = onAction,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        ChartsContent(
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

@Composable
internal fun ChartsContent(
    state: ChartsState,
    onAction: (ChartsAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    // Content implementation
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewChartsScreen() {
    AppTheme {
        ChartsScreen(
            state = ChartsState(),
            onAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
