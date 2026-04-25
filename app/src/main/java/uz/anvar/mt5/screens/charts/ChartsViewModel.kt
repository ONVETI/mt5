package uz.anvar.mt5.screens.charts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import uz.anvar.mt5.data.repository.ForexRepository
import uz.anvar.mt5.screens.charts.state.ChartsAction
import uz.anvar.mt5.screens.charts.state.ChartsSideEffect
import uz.anvar.mt5.screens.charts.state.ChartsState

internal class ChartsViewModel(
    private val forexRepository: ForexRepository,
) : ViewModel(), ContainerHost<ChartsState, ChartsSideEffect> {

    override val container: Container<ChartsState, ChartsSideEffect> = container(
        initialState = ChartsState(),
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    reduce { state.copy(isLoading = false, error = throwable.message) }
                    postSideEffect(ChartsSideEffect.Error(throwable))
                }
            }
        },
        onCreate = {
            fetchInitialData()
            observeRealTimeUpdates()
            startCountdownTimer()
        },
    )

    fun onAction(action: ChartsAction) {
        when (action) {
            is ChartsAction.NavigateBack -> onNavigateBackClicked()
            ChartsAction.ClickTrading -> onTradingClicked()
        }
    }

    private fun startCountdownTimer() = intent {
        while (true) {
            val secondsInMinute = 60
            val currentSecond = (System.currentTimeMillis() / 1000) % secondsInMinute
            val remaining = (secondsInMinute - currentSecond).toInt()
            reduce { state.copy(countdownSeconds = remaining) }
            delay(1000)
        }
    }

    private fun fetchInitialData() = intent {
        forexRepository.getEurUsdTimeSeries()
            .onStart { reduce { state.copy(isLoading = true) } }
            .catch { e ->
                reduce { state.copy(isLoading = false, error = e.message) }
                postSideEffect(ChartsSideEffect.Error(e))
            }
            .collect { candles ->
                reduce { state.copy(isLoading = false, candles = candles) }
            }
    }

    private fun observeRealTimeUpdates() = intent {
        forexRepository.observeRealTimePrice()
            .catch { /* Handle WS errors */ }
            .collect { update ->
                val currentCandles = state.candles.toMutableList()
                if (currentCandles.isNotEmpty() && update.price != null) {
                    val lastIndex = currentCandles.lastIndex
                    val lastCandle = currentCandles[lastIndex]

                    val newPrice = update.price.toFloat()
                    val updatedCandle = lastCandle.copy(
                        close = newPrice,
                        high = maxOf(lastCandle.high, newPrice),
                        low = minOf(lastCandle.low, newPrice)
                    )

                    currentCandles[lastIndex] = updatedCandle
                    reduce { state.copy(candles = currentCandles) }
                }
            }
    }


    private fun onTradingClicked() = intent {
        reduce { state.copy(isVisibleTradingContent = !state.isVisibleTradingContent) }
    }

    private fun onNavigateBackClicked() = intent {
        postSideEffect(ChartsSideEffect.NavigateBack)
    }
}
