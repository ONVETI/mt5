package uz.anvar.mt5.screens.trade

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.trade.component.TradeBottomBar
import uz.anvar.mt5.screens.trade.component.TradeTopBar
import uz.anvar.mt5.screens.trade.state.TradeAction
import uz.anvar.mt5.screens.trade.state.TradeState
import uz.anvar.mt5.ui.component.DottedDivider
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
    Column(
        modifier = modifier.padding(paddingValues)
    ) {

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text("Balance:", style = AppTheme.typography.primary16Bold)
            DottedDivider(
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp, bottom = 4.dp)
                    .weight(1f)
                    .align(Alignment.Bottom),
            )
            Text(text = "5 062 303.07", style = AppTheme.typography.primary16Bold)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text("Equity:", style = AppTheme.typography.primary16Bold)
            DottedDivider(
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp, bottom = 4.dp)
                    .weight(1f)
                    .align(Alignment.Bottom),
            )
            Text(text = "5 062 303.07", style = AppTheme.typography.primary16Bold)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text("Free margin:", style = AppTheme.typography.primary16Bold)
            DottedDivider(
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp, bottom = 4.dp)
                    .weight(1f)
                    .align(Alignment.Bottom),
            )
            Text(text = "5 062 303.07", style = AppTheme.typography.primary16Bold)
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            color = Color(0xFFE8E8E8)
        )
    }
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
