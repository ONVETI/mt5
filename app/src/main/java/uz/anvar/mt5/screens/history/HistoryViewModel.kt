package uz.anvar.mt5.screens.history

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import uz.anvar.mt5.screens.history.state.HistoryAction
import uz.anvar.mt5.screens.history.state.HistorySideEffect
import uz.anvar.mt5.screens.history.state.HistoryState
import uz.anvar.mt5.screens.history.state.HistoryTab

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
            is HistoryAction.SelectTab -> onSelectTab(action.tab)
        }
    }

    private fun onSelectTab(tab: HistoryTab) = intent {
        reduce { state.copy(selectedTab = tab) }
    }

    private fun onNavigateBackClicked() = intent {
        postSideEffect(HistorySideEffect.NavigateBack)
    }
}
