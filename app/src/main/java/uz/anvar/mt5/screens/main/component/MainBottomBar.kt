package uz.anvar.mt5.screens.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.anvar.mt5.R
import uz.anvar.mt5.screens.charts.ChartsRoute
import uz.anvar.mt5.screens.history.HistoryRoute
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.main.state.MainState
import uz.anvar.mt5.screens.messages.MessagesRoute
import uz.anvar.mt5.screens.quotes.QuotesRoute
import uz.anvar.mt5.screens.trade.TradeRoute
import uz.anvar.mt5.ui.theme.AppTheme

@Composable
internal fun MainBottomBar(
    state: MainState,
    onAction: (MainAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedContentColor = Color(0xFF0B71F1)
    val selectedIndicatorColor = Color(0xFFF1F8FE)

    val unselectedIconColor = Color(0xFF544B49)
    val unselectedTextColor = Color(0xFF000000)

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = AppTheme.colors.surfaceBackground1,
        ) {

            val isSelectedHome = state.selectedBottomNavItem == QuotesRoute
//            val homeBgColor = if (isSelectedHome) AppTheme.colors.firstColor else Color.Unspecified

            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = selectedContentColor,
                    selectedTextColor = selectedContentColor,
                    selectedIndicatorColor = selectedIndicatorColor,
                    unselectedIconColor = unselectedIconColor,
                    unselectedTextColor = unselectedTextColor,

                    disabledIconColor = Red,
                    disabledTextColor = Red,
                ),
//                modifier = Modifier.background(homeBgColor),
                selected = isSelectedHome,
                onClick = { onAction(MainAction.BottomNavItemSelected(QuotesRoute)) },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_quotes),
                        contentDescription = stringResource(id = R.string.quotes),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.quotes),
//                        style = AppTheme.typography.primary11Bold
                    )
                }
            )

            val isSelectedGames = state.selectedBottomNavItem == ChartsRoute
//            val gamesBgColor = if (isSelectedGames) AppTheme.colors.secondColor else Color.Unspecified

            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = selectedContentColor,
                    selectedTextColor = selectedContentColor,
                    selectedIndicatorColor = selectedIndicatorColor,
                    unselectedIconColor = unselectedIconColor,
                    unselectedTextColor = unselectedTextColor,

                    disabledIconColor = Red,
                    disabledTextColor = Red
                ),
//                modifier = Modifier.background(gamesBgColor),
                selected = isSelectedGames,
                onClick = { onAction(MainAction.BottomNavItemSelected(ChartsRoute)) },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_charts),
                        contentDescription = stringResource(id = R.string.charts),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.charts),
//                        style = AppTheme.typography.primary11Bold
                    )
                }
            )

            val isSelectedPuzzle = state.selectedBottomNavItem == TradeRoute
//            val puzzleBgColor = if (isSelectedPuzzle) AppTheme.colors.thirdColor else Color.Unspecified

            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = selectedContentColor,
                    selectedTextColor = selectedContentColor,
                    selectedIndicatorColor = selectedIndicatorColor,
                    unselectedIconColor = unselectedIconColor,
                    unselectedTextColor = unselectedTextColor,

                    disabledIconColor = Red,
                    disabledTextColor = Red
                ),
//                modifier = Modifier.background(puzzleBgColor),
                selected = isSelectedPuzzle,
                onClick = { onAction(MainAction.BottomNavItemSelected(TradeRoute)) },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.six_ic_trading),
                        contentDescription = stringResource(id = R.string.trade),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.trade),
//                        style = AppTheme.typography.primary11Bold
                    )
                }
            )

            val isSelectedRating = state.selectedBottomNavItem == HistoryRoute
//            val ratingBgColor = if (isSelectedRating) AppTheme.colors.fourthColor else Color.Unspecified

            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = selectedContentColor,
                    selectedTextColor = selectedContentColor,
                    selectedIndicatorColor = selectedIndicatorColor,
                    unselectedIconColor = unselectedIconColor,
                    unselectedTextColor = unselectedTextColor,

                    disabledIconColor = Red,
                    disabledTextColor = Red
                ),
//                modifier = Modifier.background(ratingBgColor),
                selected = isSelectedRating,
                onClick = { onAction(MainAction.BottomNavItemSelected(HistoryRoute)) },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_timer),
                        contentDescription = stringResource(id = R.string.history),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.history),
                        maxLines = 1,
//                        style = AppTheme.typography.primary11Bold
                    )
                }
            )

            val isSelectedProfile = state.selectedBottomNavItem == MessagesRoute
//            val profileBgColor = if (isSelectedProfile) AppTheme.colors.fifthColor else Color.Unspecified

            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = selectedContentColor,
                    selectedTextColor = selectedContentColor,
                    selectedIndicatorColor = selectedIndicatorColor,
                    unselectedIconColor = unselectedIconColor,
                    unselectedTextColor = unselectedTextColor,

                    disabledIconColor = Red,
                    disabledTextColor = Red
                ),
//                modifier = Modifier.background(profileBgColor),
                selected = isSelectedProfile,
                onClick = { onAction(MainAction.BottomNavItemSelected(MessagesRoute)) },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_menu),
                        contentDescription = stringResource(id = R.string.messages),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.messages),
//                        style = AppTheme.typography.primary11Bold
                    )
                }
            )
        }
    }

}

@Preview
@Composable
private fun PreviewMainBottomBar() {
    AppTheme {
        MainBottomBar(
            state = MainState(
                selectedBottomNavItem = ""
            ),
            onAction = {}
        )
    }
}