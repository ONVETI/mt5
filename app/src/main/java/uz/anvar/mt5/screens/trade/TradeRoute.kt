package uz.anvar.mt5.screens.trade

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
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.anvar.mt5.screens.trade.state.TradeSideEffect

@Serializable
data object TradeRoute

fun NavGraphBuilder.tradeRoute(
    navController: NavController,
) = composable<TradeRoute> {

        val viewModel: TradeViewModel = koinViewModel()
        val state by viewModel.collectAsState()
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is TradeSideEffect.NavigateBack -> navController.navigateUp()

                is TradeSideEffect.Error -> scope.launch {
                    snackbarHostState.showSnackbar(sideEffect.throwable.message ?: "Unknown error occurred")
                }
            }
        }

        TradeScreen(
            state = state,
            onAction = viewModel::onAction,
            snackbarHostState = snackbarHostState,
        )
    }
