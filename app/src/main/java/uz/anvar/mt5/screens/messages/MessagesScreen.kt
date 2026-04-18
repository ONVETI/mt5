package uz.anvar.mt5.screens.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.anvar.mt5.R
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

data class MessagesModel(
    val profilePhoto: Int,
    val fullName: String,
    val message: String,
    val date: String,
)

@Composable
internal fun MessagesContent(
    state: MessagesState,
    onAction: (MessagesAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {

    val list = listOf<MessagesModel>(
        MessagesModel(
            profilePhoto = 123,
            fullName = "Trading Platform",
            message = "Discover the new financial portal metatrader.com",
            date = "09.04"
        ),
        MessagesModel(
            profilePhoto = 123,
            fullName = "TradingView",
            message = "We have introduced a new trading platform",
            date = "09.04"
        )
    )

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(list) { model ->
            MessagesItem(model = model)
        }
    }
}

@Composable
private fun MessagesItem(model: MessagesModel) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        listOf<Color>(
                            Color(0xFF06AAE7),
                            Color(0xFF0461B3),
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_burger_two),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Row {
                Text(
                    text = model.fullName,
                    style = AppTheme.typography.primary16Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = model.date,
                    style = AppTheme.typography.primary14Regular,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = model.message,
                style = AppTheme.typography.primary14Regular,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
    }
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
