package uz.anvar.mt5.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uz.anvar.mt5.screens.charts.ChartsViewModel
import uz.anvar.mt5.screens.history.HistoryViewModel
import uz.anvar.mt5.screens.main.MainViewModel
import uz.anvar.mt5.screens.messages.MessagesViewModel
import uz.anvar.mt5.screens.quotes.QuotesViewModel
import uz.anvar.mt5.screens.trade.TradeViewModel

val screensModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::QuotesViewModel)
    viewModelOf(::ChartsViewModel)
    viewModelOf(::TradeViewModel)
    viewModelOf(::HistoryViewModel)
    viewModelOf(::MessagesViewModel)
}
