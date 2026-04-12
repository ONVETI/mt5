package uz.anvar.mt5.screens.history

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import uz.anvar.mt5.screens.history.state.HistoryAction
import uz.anvar.mt5.screens.history.state.HistorySideEffect
import uz.anvar.mt5.screens.history.state.HistoryState
import kotlinx.coroutines.CoroutineExceptionHandler

internal class HistoryViewModel : ViewModel(), ContainerHost<HistoryState, HistorySideEffect> {

    override val container: Container<HistoryState, HistorySideEffect> = container(
        initialState = HistoryState(),
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(HistorySideEffect.Error(throwable))
                }
            }
        },
        onCreate = {

        },
    )

    fun onAction(action: HistoryAction) {
        when (action) {
            is HistoryAction.NavigateBack -> onNavigateBackClicked()
        }
    }

    private fun onNavigateBackClicked() = intent {
        postSideEffect(HistorySideEffect.NavigateBack)
    }
}
