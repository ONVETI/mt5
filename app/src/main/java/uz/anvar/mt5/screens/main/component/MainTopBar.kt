package uz.anvar.mt5.screens.main.component

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
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.main.state.MainState
import uz.anvar.mt5.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainTopBar(
    state: MainState,
    onAction: (MainAction) -> Unit,
    modifier: Modifier = Modifier,
) {

}

@Composable
fun DrawerMenuButton(
    onAction: (MainAction) -> Unit,
) {
    IconButton(
        onClick = { onAction(MainAction.OpenDrawer) },
        modifier = Modifier.padding()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_burger_two),
            contentDescription = "Open menu",
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF544B49)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMainTopBar() {
    AppTheme {
        MainTopBar(
            state = MainState(selectedBottomNavItem = ""),
            onAction = {}
        )
    }
}