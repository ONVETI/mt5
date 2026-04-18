package uz.anvar.mt5.screens.quotes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.quotes.component.QuotesBottomBar
import uz.anvar.mt5.screens.quotes.component.QuotesTopBar
import uz.anvar.mt5.screens.quotes.state.QuotesAction
import uz.anvar.mt5.screens.quotes.state.QuotesState
import uz.anvar.mt5.ui.theme.AppTheme
import uz.anvar.mt5.ui.theme.avenirFontFamily


@Composable
internal fun QuotesScreen(
    state: QuotesState,
    onAction: (QuotesAction) -> Unit,
    drawerAction: (MainAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            QuotesTopBar(
                state = state,
                onAction = onAction,
                drawerAction = drawerAction
            )
        },
        bottomBar = {
            QuotesBottomBar(
                state = state,
                onAction = onAction,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        QuotesContent(
            state = state,
            onAction = onAction,
            paddingValues = paddingValues,
        )
    }
}

data class QuotesModel(
    val growIndicatorInNumber: String,
    val growIndicatorInPercent: String,
    val currencyName: String,
    val time: String,

    val cellPrice: String,
    val buyPrice: String,

    val l: String,
    val h: String,
)

@Composable
internal fun QuotesContent(
    state: QuotesState,
    onAction: (QuotesAction) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {

    val list = listOf<QuotesModel>(
        QuotesModel(
            growIndicatorInNumber = "+83",
            growIndicatorInPercent = "0.08%",
            currencyName = "EURUSD",
            time = "08:54:45",
            cellPrice = "1.18074",
            buyPrice = "1.18064",
            l = "L: 1.17963",
            h = "H: 1.18238"
        ),
        QuotesModel(
            growIndicatorInNumber = "+3713",
            growIndicatorInPercent = "0.76%",
            currencyName = "GOLD",
            time = "10:26:48",
            cellPrice = "4.8274",
            buyPrice = "4.8249",
            l = "L: 74 420.05",
            h = "H: 75 401.45"
        )
    )

    LazyColumn(
        modifier = modifier.padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(list) { model ->
            QuotesItem(model)
        }
    }
}

@Composable
private fun QuotesItem(model: QuotesModel) {
    Row(
        modifier = Modifier
            .clickable(onClick = {

            })
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Row {
                Text(
                    text = model.growIndicatorInNumber,
                    color = Color(0xFF808080),
                    fontSize = 14.sp
                )
                Text(
                    text = model.growIndicatorInPercent,
                    color = Color(0xFF1E6DD2),
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 14.sp
                )
            }

            Text(
                text = model.currencyName,
                color = Color(0xFF000000),
                style = AppTheme.typography.third16Bold,
                fontSize = 20.sp
            )

            Text(
                text = model.time,
                color = Color(0xFF808080),
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {

            MixedSizeText(
                text = model.cellPrice
            )

//            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = model.l,
                style = AppTheme.typography.primary14Regular
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {
            MixedSizeText(
                text = model.buyPrice
            )

//            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = model.h,
                style = AppTheme.typography.primary14Regular
            )
        }
    }
}

@Composable
fun MixedSizeText(
    text: String,
) {
    val blue = AppTheme.colors.surfacePrimary

    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = text.substring(0, 4),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = blue,
            modifier = Modifier.padding(bottom = 3.dp),
            fontFamily = avenirFontFamily()
        )
        Text(
            text = text.substring(4, 6),
            fontSize = 32.sp,
            color = blue,
            style = AppTheme.typography.primary32Semibold.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.Both // yuqori va pastni kesadi
                )
            ),
            fontFamily = avenirFontFamily()
        )

        Box(
            modifier = Modifier.align(Alignment.Top)
        ) {
            Text(
                text = text.takeLast(1),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = blue,
                fontFamily = avenirFontFamily()
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewQuotesScreen() {
    AppTheme {
        QuotesScreen(
            state = QuotesState(),
            onAction = {},
            drawerAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
