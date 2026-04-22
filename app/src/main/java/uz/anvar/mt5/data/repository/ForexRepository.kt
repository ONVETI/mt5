package uz.anvar.mt5.data.repository

import com.tradingview.lightweightcharts.api.series.models.CandlestickData
import com.tradingview.lightweightcharts.api.series.models.Time
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import uz.anvar.mt5.data.remote.TwelveDataApi
import uz.anvar.mt5.data.remote.TwelveDataWebSocketResponse
import uz.anvar.mt5.di.ApiKey
import java.text.SimpleDateFormat
import java.util.Locale

class ForexRepository(
    private val api: TwelveDataApi,
    private val apiKey: ApiKey,
    private val okHttpClient: OkHttpClient,
    private val json: Json,
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
            }.reversed()
            emit(candles)
        } else {
            throw Exception(response.message ?: "Unknown API error")
        }
    }

    fun observeRealTimePrice(symbol: String = "EUR/USD"): Flow<TwelveDataWebSocketResponse> = callbackFlow {
        val request = Request.Builder()
            .url("wss://ws.twelvedata.com/v1/quotes/price?apikey=${apiKey.key}")
            .build()

        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                val subscribeMsg = """
                    {
                        "action": "subscribe",
                        "params": {
                            "symbols": "$symbol"
                        }
                    }
                """.trimIndent()
                webSocket.send(subscribeMsg)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                try {
                    val data = json.decodeFromString<TwelveDataWebSocketResponse>(text)
                    if (data.event == "price") {
                        trySend(data)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                close(t)
            }
        }

        val webSocket = okHttpClient.newWebSocket(request, listener)

        awaitClose {
            webSocket.close(1000, "Closed")
        }
    }
}
