package uz.anvar.mt5.screens.quotes.state

import kotlin.jvm.JvmInline

internal sealed interface QuotesSideEffect {

    data object NavigateBack : QuotesSideEffect

    @JvmInline
    value class Error(val throwable: Throwable) : QuotesSideEffect
}
