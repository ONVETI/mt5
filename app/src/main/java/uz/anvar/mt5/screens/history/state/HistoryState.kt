package uz.anvar.mt5.screens.history.state

internal data class HistoryState(
    val isLoading: Boolean = false,
    val selectedTab: HistoryTab = HistoryTab.POSITIONS,
)

enum class HistoryTab {
    POSITIONS, ORDERS, DEALS
}
