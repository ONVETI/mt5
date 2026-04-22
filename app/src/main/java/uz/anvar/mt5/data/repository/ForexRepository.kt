package uz.anvar.mt5.data.repository

import com.tradingview.lightweightcharts.api.series.models.CandlestickData
import com.tradingview.lightweightcharts.api.series.models.Time
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.anvar.mt5.data.remote.TwelveDataApi
import uz.anvar.mt5.di.ApiKey
import java.text.SimpleDateFormat
import java.util.Locale

class ForexRepository(
    private val api: TwelveDataApi,
    private val apiKey: ApiKey,
) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    fun getEurUsdTimeSeries(interval: String = "1min"): Flow<List<CandlestickData>> = flow {
        val response = api.getTimeSeries("EUR/USD", interval, apiKey.key)
        if (response.status == "ok" && response.values != null) {
            val candles = response.values.map { candle ->
                CandlestickData(
                    time = Time.Utc(dateFormat.parse(candle.datetime)!!.time / 1000),
                    open = candle.open.toFloat(),
                    high = candle.high.toFloat(),
                    low = candle.low.toFloat(),
                    close = candle.close.toFloat(),
                )
            }.reversed() // Lightweight Charts usually expects data in chronological order
            emit(candles)
        } else {
            throw Exception(response.message ?: "Unknown API error")
        }
    }
}
