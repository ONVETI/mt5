package uz.anvar.mt5.screens.history.state

import kotlin.jvm.JvmInline

internal sealed interface HistorySideEffect {

    data object NavigateBack : HistorySideEffect

    @JvmInline
    value class Error(val throwable: Throwable) : HistorySideEffect
}
