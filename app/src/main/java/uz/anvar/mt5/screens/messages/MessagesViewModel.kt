package uz.anvar.mt5.screens.messages

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import uz.anvar.mt5.screens.messages.state.MessagesAction
import uz.anvar.mt5.screens.messages.state.MessagesSideEffect
import uz.anvar.mt5.screens.messages.state.MessagesState
import kotlinx.coroutines.CoroutineExceptionHandler

internal class MessagesViewModel : ViewModel(), ContainerHost<MessagesState, MessagesSideEffect> {

    override val container: Container<MessagesState, MessagesSideEffect> = container(
        initialState = MessagesState(),
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(MessagesSideEffect.Error(throwable))
                }
            }
        },
        onCreate = {

        },
    )

    fun onAction(action: MessagesAction) {
        when (action) {
            is MessagesAction.NavigateBack -> onNavigateBackClicked()
        }
    }

    private fun onNavigateBackClicked() = intent {
        postSideEffect(MessagesSideEffect.NavigateBack)
    }
}
