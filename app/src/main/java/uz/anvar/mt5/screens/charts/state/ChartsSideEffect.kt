package uz.anvar.mt5.screens.charts.state

import kotlin.jvm.JvmInline

internal sealed interface ChartsSideEffect {

    data object NavigateBack : ChartsSideEffect

    @JvmInline
    value class Error(val throwable: Throwable) : ChartsSideEffect
}
