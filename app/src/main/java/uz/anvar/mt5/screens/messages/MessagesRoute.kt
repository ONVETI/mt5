package uz.anvar.mt5.screens.messages

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
import uz.anvar.mt5.screens.main.MainViewModel
import uz.anvar.mt5.screens.messages.state.MessagesSideEffect

@Serializable
data object MessagesRoute

fun NavGraphBuilder.messagesRoute(
    navController: NavController,
) = composable<MessagesRoute> {

    val viewModel: MessagesViewModel = koinViewModel()
    val mainViewModel: MainViewModel = koinActivityViewModel()

    val state by viewModel.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is MessagesSideEffect.NavigateBack -> navController.navigateUp()

            is MessagesSideEffect.Error -> scope.launch {
                snackbarHostState.showSnackbar(
                    sideEffect.throwable.message ?: "Unknown error occurred"
                )
            }
        }
    }

    MessagesScreen(
        state = state,
        onAction = viewModel::onAction,
        drawerAction = mainViewModel::onAction,
        snackbarHostState = snackbarHostState,
    )
}
