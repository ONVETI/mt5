package uz.anvar.mt5.screens.charts.state

import com.tradingview.lightweightcharts.api.series.models.CandlestickData

data class ChartsState(
    val isLoading: Boolean = false,
    val isVisibleTradingContent: Boolean = false,
    val candles: List<CandlestickData> = emptyList(),
    val error: String? = null,
)
