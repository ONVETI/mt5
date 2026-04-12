package uz.anvar.mt5.screens.main

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.main.state.MainSideEffect
import uz.anvar.mt5.screens.main.state.MainState
import kotlinx.coroutines.CoroutineExceptionHandler

internal class MainViewModel : ViewModel(), ContainerHost<MainState, MainSideEffect> {

    override val container: Container<MainState, MainSideEffect> = container(
        initialState = MainState(),
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(MainSideEffect.Error(throwable))
                }
            }
        },
        onCreate = {

        },
    )

    fun onAction(action: MainAction) {
        when (action) {
            is MainAction.NavigateBack -> onNavigateBackClicked()
        }
    }

    private fun onNavigateBackClicked() = intent {
        postSideEffect(MainSideEffect.NavigateBack)
    }
}
