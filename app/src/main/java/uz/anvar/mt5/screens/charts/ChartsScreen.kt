package uz.anvar.mt5.screens.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import com.tradingview.lightweightcharts.api.chart.models.color.toIntColor
import com.tradingview.lightweightcharts.api.interfaces.SeriesApi
import com.tradingview.lightweightcharts.api.options.models.candlestickSeriesOptions
import com.tradingview.lightweightcharts.api.options.models.crosshairLineOptions
import com.tradingview.lightweightcharts.api.options.models.crosshairOptions
import com.tradingview.lightweightcharts.api.options.models.gridLineOptions
import com.tradingview.lightweightcharts.api.options.models.gridOptions
import com.tradingview.lightweightcharts.api.options.models.layoutOptions
import com.tradingview.lightweightcharts.api.options.models.localizationOptions
import com.tradingview.lightweightcharts.api.series.enums.LineStyle
import com.tradingview.lightweightcharts.api.series.enums.LineWidth
import com.tradingview.lightweightcharts.view.ChartsView
import uz.anvar.mt5.R
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

    var candlestickSeries by remember { mutableStateOf<SeriesApi?>(null) }
    var labelYOffset by remember { mutableStateOf<Float?>(null) }
    val density = LocalDensity.current

    LaunchedEffect(state.candles, state.countdownSeconds) {
        val series = candlestickSeries ?: return@LaunchedEffect
        if (state.candles.isNotEmpty()) {
            val lastCandle = state.candles.last()
            series.update(lastCandle)

            series.priceToCoordinate(lastCandle.close) { coordinate ->
                labelYOffset = coordinate
            }
        }
    }

    // Initial data set
    LaunchedEffect(state.isLoading) {
        val series = candlestickSeries ?: return@LaunchedEffect
        if (!state.isLoading && state.candles.isNotEmpty()) {
            series.setData(state.candles)

            state.candles.lastOrNull()?.let { last ->
                series.priceToCoordinate(last.close) { coordinate ->
                    labelYOffset = coordinate
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        if (state.isVisibleTradingContent) {
            TradeBar(state)
        }

        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = { context ->
                    ChartsView(context).apply {
                        api.applyOptions {
                            layout = layoutOptions {
                                setBackgroundColor(android.graphics.Color.BLUE)
                                textColor = android.graphics.Color.RED.toIntColor()
                            }
                            grid = gridOptions {
                                vertLines = gridLineOptions {
                                    color = android.graphics.Color.GREEN.toIntColor()
                                    style = LineStyle.DOTTED
                                    visible = true
                                }
                                horzLines = gridLineOptions {
                                    color = android.graphics.Color.YELLOW.toIntColor()
                                    style = LineStyle.DOTTED
                                    visible = true
                                }
                            }
                            crosshair = crosshairOptions {
                                vertLine = crosshairLineOptions {
                                    color = "#ff00d4".toColorInt().toIntColor()
                                    width = LineWidth.FOUR
                                    style = LineStyle.LARGE_DASHED
                                    labelVisible = true
                                    visible = true
                                    labelBackgroundColor = android.graphics.Color.BLACK.toIntColor()
                                }
                                horzLine = crosshairLineOptions {
                                    color = "#ff00d4".toColorInt().toIntColor()
                                    width = LineWidth.FOUR
                                    style = LineStyle.LARGE_DASHED
                                    labelVisible = true
                                    visible = true
                                    labelBackgroundColor = android.graphics.Color.BLACK.toIntColor()
                                }
                            }

                            localization = localizationOptions {
                                locale = "en-US"
                            }
                        }

                        api.addCandlestickSeries(
                            onSeriesCreated = { series ->
                                candlestickSeries = series
                                series.applyOptions(
                                    candlestickSeriesOptions {

                                        visible = true
                                        wickVisible = true
                                        priceLineVisible = true
                                        lastValueVisible = true
                                        borderVisible = true
                                        baseLineVisible = true

                                        wickColor = "#ef5350".toColorInt().toIntColor()
                                        upColor = "#26a69a".toColorInt().toIntColor()
                                        downColor = "#ef5350".toColorInt().toIntColor()
                                        borderUpColor = "#26a69a".toColorInt().toIntColor()
                                        borderDownColor = "#ef5350".toColorInt().toIntColor()
                                        wickUpColor = "#26a69a".toColorInt().toIntColor()
                                        wickDownColor = "#ef5350".toColorInt().toIntColor()
                                    }
                                )
                            }
                        )
                    }
                },
                modifier = modifier.fillMaxSize(),
                update = { view -> }
            )

            // Overlay Symbol and Description
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = state.symbol,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = state.interval,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = state.description,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            // Price and Countdown Label (Simplified overlay)
            state.candles.lastOrNull()?.let { last ->
                val yOffset = labelYOffset ?: 0f
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 48.dp)
                        .offset(y = with(density) { (yOffset).toDp() - 10.dp })
                ) {
                    val countdownFormatted = String.format(
                        "%02d:%02d",
                        state.countdownSeconds / 60,
                        state.countdownSeconds % 60
                    )
                    Text(
                        text = "${last.close} ($countdownFormatted)",
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .background(
                                if (last.close >= last.open) {
                                    Color(0xFF26a69a)
                                } else {
                                    Color(0xFFef5350)
                                }
                            )
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TradeBar(state: ChartsState) {
    val lastPrice = state.candles.lastOrNull()?.close?.toString() ?: "1.1792⁴"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize()
                .background(AppTheme.colors.surfacePrimary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SELL",
                color = Color.White,
                fontSize = 9.sp,
                style = AppTheme.typography.primary14Regular,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp),
            )

            Text(
                text = lastPrice,
                color = Color.White,
                style = AppTheme.typography.primary18Semibold,
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier
                .weight(0.5f)
                .wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_bottom),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            }

            var amount = remember { TextFieldValue("100") }

            // TEXTFIELD
            TextField(
                value = amount,
                onValueChange = {
                    amount = TextFieldValue(it.text)
                },
                modifier = Modifier.width(80.dp),
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )

            IconButton(
                onClick = { },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_bottom),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            }
        }

        Row(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize()
                .background(color = AppTheme.colors.surfacePrimary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "BUY",
                color = Color.White,
                fontSize = 9.sp,
                style = AppTheme.typography.primary14Regular,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp),
            )

            Text(
                text = lastPrice,
                color = Color.White,
                style = AppTheme.typography.primary18Semibold,
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}

// I will provide a more robust implementation in the next turn if I hit a snag, 
// but let's try a better generation logic.

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//private fun PreviewChartsScreen() {
//    AppTheme {
//        ChartsScreen(
//            state = ChartsState(),
//            onAction = {},
//            drawerAction = {},
//            snackbarHostState = SnackbarHostState()
//        )
//    }
//}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun PreviewTradeBar() {
    AppTheme {
        Column() {
            TradeBar(state = ChartsState())
        }
    }
}
