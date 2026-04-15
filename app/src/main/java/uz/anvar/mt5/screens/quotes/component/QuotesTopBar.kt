package uz.anvar.mt5.screens.quotes.component

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
import uz.anvar.mt5.screens.main.component.DrawerMenuButton
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.quotes.state.QuotesAction
import uz.anvar.mt5.screens.quotes.state.QuotesState
import uz.anvar.mt5.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuotesTopBar(
    state: QuotesState,
    onAction: (QuotesAction) -> Unit,
    drawerAction: (MainAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text("Quotes", style = AppTheme.typography.secondary18Medium)
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
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Open menu",
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF544B49)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.padding()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pen),
                    contentDescription = "Open menu",
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF544B49)
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewQuotesTopBar() {
    AppTheme {
        QuotesTopBar(
            state = QuotesState(),
            onAction = {},
            drawerAction = {}
        )
    }
}
