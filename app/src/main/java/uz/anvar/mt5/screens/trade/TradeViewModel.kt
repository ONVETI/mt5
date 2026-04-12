package uz.anvar.mt5.screens.trade

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import uz.anvar.mt5.screens.trade.state.TradeAction
import uz.anvar.mt5.screens.trade.state.TradeSideEffect
import uz.anvar.mt5.screens.trade.state.TradeState
import kotlinx.coroutines.CoroutineExceptionHandler

internal class TradeViewModel : ViewModel(), ContainerHost<TradeState, TradeSideEffect> {

    override val container: Container<TradeState, TradeSideEffect> = container(
        initialState = TradeState(),
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(TradeSideEffect.Error(throwable))
                }
            }
        },
        onCreate = {

        },
    )

    fun onAction(action: TradeAction) {
        when (action) {
            is TradeAction.NavigateBack -> onNavigateBackClicked()
        }
    }

    private fun onNavigateBackClicked() = intent {
        postSideEffect(TradeSideEffect.NavigateBack)
    }
}
