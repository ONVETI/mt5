package uz.anvar.mt5.screens.charts.state

import com.tradingview.lightweightcharts.api.series.models.CandlestickData

data class ChartsState(
    val isLoading: Boolean = false,
    val isVisibleTradingContent: Boolean = false,
    val candles: List<CandlestickData> = emptyList(),
    val error: String? = null,
    val symbol: String = "EURUSD",
    val interval: String = "M1",
    val description: String = "Euro vs US Dollar",
    val countdownSeconds: Int = 0,
)

