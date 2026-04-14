package uz.anvar.mt5.screens.main.state

internal sealed interface MainAction {

    data object NavigateBack : MainAction
    data class BottomNavItemSelected(val item: Any) : MainAction

}
