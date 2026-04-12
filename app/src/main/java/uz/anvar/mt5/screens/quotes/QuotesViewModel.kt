package uz.anvar.mt5.screens.quotes

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import uz.anvar.mt5.screens.quotes.state.QuotesAction
import uz.anvar.mt5.screens.quotes.state.QuotesSideEffect
import uz.anvar.mt5.screens.quotes.state.QuotesState
import kotlinx.coroutines.CoroutineExceptionHandler

internal class QuotesViewModel : ViewModel(), ContainerHost<QuotesState, QuotesSideEffect> {

    override val container: Container<QuotesState, QuotesSideEffect> = container(
        initialState = QuotesState(),
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(QuotesSideEffect.Error(throwable))
                }
            }
        },
        onCreate = {

        },
    )

    fun onAction(action: QuotesAction) {
        when (action) {
            is QuotesAction.NavigateBack -> onNavigateBackClicked()
        }
    }

    private fun onNavigateBackClicked() = intent {
        postSideEffect(QuotesSideEffect.NavigateBack)
    }
}
