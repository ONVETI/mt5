package uz.anvar.mt5.screens.charts

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import uz.anvar.mt5.screens.charts.state.ChartsAction
import uz.anvar.mt5.screens.charts.state.ChartsSideEffect
import uz.anvar.mt5.screens.charts.state.ChartsState
import kotlinx.coroutines.CoroutineExceptionHandler

internal class ChartsViewModel : ViewModel(), ContainerHost<ChartsState, ChartsSideEffect> {

    override val container: Container<ChartsState, ChartsSideEffect> = container(
        initialState = ChartsState(),
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(ChartsSideEffect.Error(throwable))
                }
            }
        },
        onCreate = {

        },
    )

    fun onAction(action: ChartsAction) {
        when (action) {
            is ChartsAction.NavigateBack -> onNavigateBackClicked()
        }
    }

    private fun onNavigateBackClicked() = intent {
        postSideEffect(ChartsSideEffect.NavigateBack)
    }
}
