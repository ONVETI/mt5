package uz.anvar.mt5.screens.quotes

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
import uz.anvar.mt5.screens.quotes.state.QuotesSideEffect

@Serializable
data object QuotesRoute

fun NavGraphBuilder.quotesRoute(
    navController: NavController,
) = composable<QuotesRoute> {

        val viewModel: QuotesViewModel = koinViewModel()
        val state by viewModel.collectAsState()
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is QuotesSideEffect.NavigateBack -> navController.navigateUp()

                is QuotesSideEffect.Error -> scope.launch {
                    snackbarHostState.showSnackbar(sideEffect.throwable.message ?: "Unknown error occurred")
                }
            }
        }

        QuotesScreen(
            state = state,
            onAction = viewModel::onAction,
            snackbarHostState = snackbarHostState,
        )
    }
