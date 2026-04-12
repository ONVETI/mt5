package uz.anvar.mt5.screens.main.state

import kotlin.jvm.JvmInline

internal sealed interface MainSideEffect {

    data object NavigateBack : MainSideEffect

    @JvmInline
    value class Error(val throwable: Throwable) : MainSideEffect
}
