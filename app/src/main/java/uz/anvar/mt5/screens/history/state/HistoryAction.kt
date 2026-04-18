package uz.anvar.mt5.screens.history.state

internal sealed interface HistoryAction {

    data object NavigateBack : HistoryAction
    data class SelectTab(val tab: HistoryTab) : HistoryAction

}
