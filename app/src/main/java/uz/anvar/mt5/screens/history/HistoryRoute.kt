package uz.anvar.mt5.screens.history

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.viewmodel.koinActivityViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.anvar.mt5.screens.history.state.HistorySideEffect
import uz.anvar.mt5.screens.main.MainViewModel

@Serializable
data object HistoryRoute

fun NavGraphBuilder.historyRoute(
    navController: NavController,
) = composable<HistoryRoute> {

    val viewModel: HistoryViewModel = koinViewModel()
    val mainViewModel: MainViewModel = koinActivityViewModel()
    val state by viewModel.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is HistorySideEffect.NavigateBack -> navController.navigateUp()

            is HistorySideEffect.Error -> scope.launch {
                snackbarHostState.showSnackbar(
                    sideEffect.throwable.message ?: "Unknown error occurred"
                )
            }
        }
    }

    HistoryScreen(
        state = state,
        onAction = viewModel::onAction,
        drawerAction = mainViewModel::onAction,
        snackbarHostState = snackbarHostState,
    )
}
