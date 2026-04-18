package uz.anvar.mt5.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.anvar.mt5.R
import uz.anvar.mt5.screens.history.component.HistoryBottomBar
import uz.anvar.mt5.screens.history.component.HistoryTopBar
import uz.anvar.mt5.screens.history.state.HistoryAction
import uz.anvar.mt5.screens.history.state.HistoryState
import uz.anvar.mt5.screens.history.state.HistoryTab
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.ui.theme.AppTheme

@Composable
internal fun HistoryScreen(
    state: HistoryState,
    onAction: (HistoryAction) -> Unit,
    drawerAction: (MainAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            HistoryTopBar(
                state = state,
                onAction = onAction,
                drawerAction = drawerAction
            )
        },
        bottomBar = {
            HistoryBottomBar(
                state = state,
                onAction = onAction,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        HistoryContent(
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

@Composable
internal fun HistoryContent(
    state: HistoryState,
    onAction: (HistoryAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val tabs = HistoryTab.entries
    val selectedTabIndex = state.selectedTab.ordinal

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            contentColor = Color.White,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = AppTheme.colors.surfacePrimary
                    )
                }
            },
            divider = {}
        ) {
            tabs.forEach { tab ->
                Tab(
                    selected = state.selectedTab == tab,
                    onClick = { onAction(HistoryAction.SelectTab(tab)) },
                    text = {
                        Text(
                            text = tab.name,
                            style = if (state.selectedTab == tab) {
                                AppTheme.typography.secondary14Medium
                            } else {
                                AppTheme.typography.secondary14Regular
                            },
                            color = if (state.selectedTab == tab) {
                                Color.Black
                            } else {
                                Color(0xFF808080)
                            }
                        )
                    }
                )
            }
        }

        // Tab content
        when (state.selectedTab) {
            HistoryTab.POSITIONS -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_not_access),
                        contentDescription = "",
                        modifier = Modifier.size(120.dp),
                        tint = Color(0xFFDEDFE1)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Empty history",
                        modifier = Modifier.padding(16.dp),
                        style = AppTheme.typography.primary14Regular,
                        color = Color(0xFFB8B8B8)
                    )
                }
            }

            HistoryTab.ORDERS -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_not_access),
                        contentDescription = "",
                        modifier = Modifier.size(120.dp),
                        tint = Color(0xFFDEDFE1)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Empty history",
                        modifier = Modifier.padding(16.dp),
                        style = AppTheme.typography.primary14Regular,
                        color = Color(0xFFB8B8B8)
                    )
                }
            }

            HistoryTab.DEALS -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_not_access),
                        contentDescription = "",
                        modifier = Modifier.size(120.dp),
                        tint = Color(0xFFDEDFE1)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Empty history",
                        modifier = Modifier.padding(16.dp),
                        style = AppTheme.typography.primary14Regular,
                        color = Color(0xFFB8B8B8)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewHistoryScreen() {
    AppTheme {
        HistoryScreen(
            state = HistoryState(),
            onAction = {},
            drawerAction = {},
            snackbarHostState = SnackbarHostState(),
        )
    }
}
