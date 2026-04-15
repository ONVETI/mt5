package uz.anvar.mt5.screens.main.state

sealed interface MainAction {

    data object NavigateBack : MainAction
    data class BottomNavItemSelected(val item: Any) : MainAction
    
    // Drawer actions
    data object OpenDrawer : MainAction
    data object CloseDrawer : MainAction
    data class DrawerItemSelected(val item: Any) : MainAction

}
