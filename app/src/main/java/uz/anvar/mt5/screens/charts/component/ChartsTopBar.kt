package uz.anvar.mt5.screens.charts.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.anvar.mt5.R
import uz.anvar.mt5.screens.charts.state.ChartsAction
import uz.anvar.mt5.screens.charts.state.ChartsState
import uz.anvar.mt5.screens.main.component.DrawerMenuButton
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChartsTopBar(
    state: ChartsState,
    onAction: (ChartsAction) -> Unit,
    drawerAction: (MainAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            DrawerMenuButton(onAction = drawerAction)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        actions = {
            IconButton(
                onClick = { },
                modifier = Modifier.padding(),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cross_shair),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
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
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.padding()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_m_one),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.padding()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.two_pending_order),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.padding()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.six_ic_trading),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewChartsTopBar() {
    AppTheme {
        ChartsTopBar(
            state = ChartsState(),
            onAction = {},
            drawerAction = {}
        )
    }
}