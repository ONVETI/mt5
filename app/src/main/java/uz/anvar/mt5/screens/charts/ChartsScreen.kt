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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.tradingview.lightweightcharts.api.chart.models.color.toIntColor
import com.tradingview.lightweightcharts.api.options.models.layoutOptions
import com.tradingview.lightweightcharts.api.options.models.localizationOptions
import com.tradingview.lightweightcharts.api.series.models.HistogramData
import com.tradingview.lightweightcharts.api.series.models.Time
import com.tradingview.lightweightcharts.api.series.models.WhitespaceData
import com.tradingview.lightweightcharts.runtime.plugins.DateTimeFormat
import com.tradingview.lightweightcharts.runtime.plugins.PriceFormatter
import com.tradingview.lightweightcharts.runtime.plugins.TimeFormatter
import com.tradingview.lightweightcharts.view.ChartsView
import uz.anvar.mt5.screens.charts.component.ChartsBottomBar
import uz.anvar.mt5.screens.charts.component.ChartsTopBar
import uz.anvar.mt5.screens.charts.state.ChartsAction
import uz.anvar.mt5.screens.charts.state.ChartsState
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.ui.theme.AppTheme

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
    AndroidView(
        factory = { context ->
            ChartsView(context).apply {
                api.applyOptions {
                    layout = layoutOptions {
                        setBackgroundColor(Color.LTGRAY)
                        textColor = Color.BLACK.toIntColor()
                    }
                    localization = localizationOptions {
                        locale = "ru-RU"
                        priceFormatter = PriceFormatter(template = "{price:#2:#3}$")
                        timeFormatter = TimeFormatter(
                            locale = "ru-RU",
                            dateTimeFormat = DateTimeFormat.DATE_TIME
                        )
                    }
                }

                api.addHistogramSeries(
                    onSeriesCreated = { series ->
                        val data = listOf(
                            HistogramData(Time.BusinessDay(2019, 6, 11), 40.01f),
                            HistogramData(Time.BusinessDay(2019, 6, 12), 52.38f),
                            HistogramData(Time.BusinessDay(2019, 6, 13), 36.30f),
                            HistogramData(Time.BusinessDay(2019, 6, 14), 34.48f),
                            WhitespaceData(Time.BusinessDay(2019, 6, 15)),
                            WhitespaceData(Time.BusinessDay(2019, 6, 16)),
                            HistogramData(Time.BusinessDay(2019, 6, 17), 41.50f),
                            HistogramData(Time.BusinessDay(2019, 6, 18), 34.82f)
                        )
                        series.setData(data)
                    }
                )
            }
        },
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        update = { view ->
            // Update the view when state changes
        }
    )
}

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
