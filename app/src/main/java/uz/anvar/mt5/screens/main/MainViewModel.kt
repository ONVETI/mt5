package uz.anvar.mt5.screens.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import uz.anvar.mt5.screens.charts.ChartsRoute
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.main.state.MainSideEffect
import uz.anvar.mt5.screens.main.state.MainState

internal class MainViewModel : ViewModel(), ContainerHost<MainState, MainSideEffect> {

    override val container: Container<MainState, MainSideEffect> = container(
        initialState = MainState(selectedBottomNavItem = ChartsRoute),
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
            is MainAction.BottomNavItemSelected -> onBottomNavItemSelected(action.item)
            is MainAction.OpenDrawer -> onOpenDrawer()
            is MainAction.CloseDrawer -> onCloseDrawer()
            is MainAction.DrawerItemSelected -> onDrawerItemSelected(action.item)
        }
    }

    private fun onNavigateBackClicked() = intent {
        postSideEffect(MainSideEffect.NavigateBack)
    }

    private fun onBottomNavItemSelected(item: Any) = intent {
        reduce { state.copy(selectedBottomNavItem = item) }
    }
    
    private fun onOpenDrawer() = intent {
        reduce { state.copy(isDrawerOpen = true) }
    }
    
    private fun onCloseDrawer() = intent {
        reduce { state.copy(isDrawerOpen = false) }
    }
    
    private fun onDrawerItemSelected(item: Any) = intent {
        reduce {
            state.copy(
                selectedBottomNavItem = item,
                isDrawerOpen = false
            )
        }
    }
}
