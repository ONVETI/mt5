package uz.anvar.mt5.screens.history.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.anvar.mt5.R
import uz.anvar.mt5.screens.history.state.HistoryAction
import uz.anvar.mt5.screens.history.state.HistoryState
import uz.anvar.mt5.screens.main.component.DrawerMenuButton
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HistoryTopBar(
    state: HistoryState,
    onAction: (HistoryAction) -> Unit,
    drawerAction: (MainAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Column {
                Text("History", style = AppTheme.typography.secondary14Medium)
                Text(
                    "All symbols",
                    style = AppTheme.typography.secondary18Medium,
                    color = Color(0xFF808080)
                )
            }
        },
        navigationIcon = {
            DrawerMenuButton(onAction = drawerAction)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        actions = {
            IconButton(
                onClick = { },
                modifier = Modifier.padding()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_question),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF544B49)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.padding()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_exchange),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF544B49)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.padding()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pause),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF544B49)
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHistoryTopBar() {
    AppTheme {
        HistoryTopBar(
            state = HistoryState(),
            onAction = {},
            drawerAction = {}
        )
    }
}