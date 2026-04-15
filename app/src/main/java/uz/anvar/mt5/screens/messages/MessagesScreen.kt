package uz.anvar.mt5.screens.messages

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.messages.component.MessagesBottomBar
import uz.anvar.mt5.screens.messages.component.MessagesTopBar
import uz.anvar.mt5.screens.messages.state.MessagesAction
import uz.anvar.mt5.screens.messages.state.MessagesState
import uz.anvar.mt5.ui.theme.AppTheme

@Composable
internal fun MessagesScreen(
    state: MessagesState,
    onAction: (MessagesAction) -> Unit,
    drawerAction: (MainAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            MessagesTopBar(
                state = state,
                onAction = onAction,
                drawerAction = drawerAction
            )
        },
        bottomBar = {
            MessagesBottomBar(
                state = state,
                onAction = onAction,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        MessagesContent(
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

@Composable
internal fun MessagesContent(
    state: MessagesState,
    onAction: (MessagesAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    // Content implementation
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewMessagesScreen() {
    AppTheme {
        MessagesScreen(
            state = MessagesState(),
            onAction = {},
            drawerAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
