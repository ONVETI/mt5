package uz.anvar.mt5.screens.trade.state

import kotlin.jvm.JvmInline

internal sealed interface TradeSideEffect {

    data object NavigateBack : TradeSideEffect

    @JvmInline
    value class Error(val throwable: Throwable) : TradeSideEffect
}
