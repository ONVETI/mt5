package uz.anvar.mt5.screens.charts

import android.graphics.Color
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.tradingview.lightweightcharts.api.chart.models.color.toIntColor
import com.tradingview.lightweightcharts.api.interfaces.SeriesApi
import com.tradingview.lightweightcharts.api.options.models.layoutOptions
import com.tradingview.lightweightcharts.api.options.models.localizationOptions
import com.tradingview.lightweightcharts.api.series.models.CandlestickData
import com.tradingview.lightweightcharts.api.series.models.Time
import com.tradingview.lightweightcharts.view.ChartsView
import kotlinx.coroutines.delay
import uz.anvar.mt5.screens.charts.component.ChartsBottomBar
import uz.anvar.mt5.screens.charts.component.ChartsTopBar
import uz.anvar.mt5.screens.charts.state.ChartsAction
import uz.anvar.mt5.screens.charts.state.ChartsState
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.ui.theme.AppTheme
import java.util.Calendar
import kotlin.random.Random

@Composable
internal fun ChartsScreen(
    state: ChartsState,
    onAction: (ChartsAction) -> Unit,
    drawerAction: (MainAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            ChartsTopBar(
                state = state,
                onAction = onAction,
                drawerAction = drawerAction
            )
        },
        bottomBar = {
            ChartsBottomBar(
                state = state,
                onAction = onAction,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        ChartsContent(
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

@Composable
internal fun ChartsContent(
    state: ChartsState,
    onAction: (ChartsAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    var candlestickSeries by remember { mutableStateOf<SeriesApi?>(null) }
    val updatesPerCandle = 5

    LaunchedEffect(candlestickSeries) {
        val series = candlestickSeries ?: return@LaunchedEffect

        // Initial data
        val initialData = mutableListOf<CandlestickData>()
        var currentPrice = 100f
        val calendar = Calendar.getInstance().apply {
            set(2024, 0, 1, 12, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }

        for (i in 0 until 100) {
            val open = currentPrice
            val close = currentPrice + (Random.nextFloat() - 0.5f) * 4f
            val high = maxOf(open, close) + Random.nextFloat() * 2f
            val low = minOf(open, close) - Random.nextFloat() * 2f

            initialData.add(
                CandlestickData(
                    time = Time.Utc(calendar.timeInMillis / 1000),
                    open = open,
                    high = high,
                    low = low,
                    close = close
                )
            )
            currentPrice = close
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        series.setData(initialData)

        // Real-time updates simulation
        var lastCandleOpen = currentPrice
        var lastCandleHigh = currentPrice
        var lastCandleLow = currentPrice
        var lastCandleClose = currentPrice
        var ticksInCurrentCandle = 0

        while (true) {
            delay(500) // Update every 500ms

            val tickDiff = (Random.nextFloat() - 0.5f) * 2f
            lastCandleClose += tickDiff
            lastCandleHigh = maxOf(lastCandleHigh, lastCandleClose)
            lastCandleLow = minOf(lastCandleLow, lastCandleClose)

            val currentCandle = CandlestickData(
                time = Time.Utc(calendar.timeInMillis / 1000),
                open = lastCandleOpen,
                high = lastCandleHigh,
                low = lastCandleLow,
                close = lastCandleClose
            )
            series.update(currentCandle)

            ticksInCurrentCandle++

            if (ticksInCurrentCandle >= updatesPerCandle) {
                // Prepare for the NEXT candle
                calendar.add(Calendar.DAY_OF_YEAR, 1)
                lastCandleOpen = lastCandleClose
                lastCandleHigh = lastCandleClose
                lastCandleLow = lastCandleClose
                ticksInCurrentCandle = 0
            }
        }
    }

    AndroidView(
        factory = { context ->
            ChartsView(context).apply {
                api.applyOptions {
                    layout = layoutOptions {
                        setBackgroundColor(Color.WHITE)
                        textColor = Color.BLACK.toIntColor()
                    }
                    localization = localizationOptions {
                        locale = "en-US"
                    }
                }

                api.addCandlestickSeries(
                    onSeriesCreated = { series ->
                        candlestickSeries = series

                        // Let's do a better simulation here instead of LaunchedEffect if needed,
                        // but LaunchedEffect is cleaner for Compose.
                    }
                )
            }
        },
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        update = { view -> }
    )
}

// I will provide a more robust implementation in the next turn if I hit a snag, 
// but let's try a better generation logic.


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewChartsScreen() {
    AppTheme {
        ChartsScreen(
            state = ChartsState(),
            onAction = {},
            drawerAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
