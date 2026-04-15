package uz.anvar.mt5.screens.main.state

internal data class MainState(
    val isLoading: Boolean = false,
    val selectedBottomNavItem: Any,
    val isDrawerOpen: Boolean = false,
)
