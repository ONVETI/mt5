package uz.anvar.mt5.screens.messages.state

import kotlin.jvm.JvmInline

internal sealed interface MessagesSideEffect {

    data object NavigateBack : MessagesSideEffect

    @JvmInline
    value class Error(val throwable: Throwable) : MessagesSideEffect
}
