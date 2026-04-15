package uz.anvar.mt5.screens.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.anvar.mt5.R
import uz.anvar.mt5.screens.charts.ChartsRoute
import uz.anvar.mt5.screens.history.HistoryRoute
import uz.anvar.mt5.screens.main.state.MainAction
import uz.anvar.mt5.screens.messages.MessagesRoute
import uz.anvar.mt5.screens.quotes.QuotesRoute
import uz.anvar.mt5.screens.trade.TradeRoute
import uz.anvar.mt5.ui.theme.AppTheme

@Composable
internal fun DrawerContent(
    state: Any,
    onAction: (MainAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.82f)
            .fillMaxHeight()
            .background(AppTheme.colors.surfaceBackground1)
            .padding(horizontal = 16.dp)
            .safeDrawingPadding(),
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(R.drawable.ic_logo_bg_white),
                contentDescription = "",
                modifier = Modifier.size(40.dp),
                alignment = Alignment.TopStart
            )

            Spacer(modifier = Modifier.width(32.dp))

            Column(verticalArrangement = Arrangement.Center) {
                Text("Ism Familiya", fontSize = 14.sp, lineHeight = 0.02.sp)
                Text(
                    "105565494 - MetaQuotes-Demo",
                    fontSize = 12.sp,
                    color = Color(0xFF808080),
                    lineHeight = 0.02.sp
                )

                Text(
                    text = "Manage accounts",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF146FE1),
                    modifier = Modifier.padding(vertical = 18.dp)
                )
            }
        }

        HorizontalDivider(
            color = Color(0xFFE0E0E0),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Navigation Items
        DrawerItem(
            icon = R.drawable.six_ic_trading,
            label = stringResource(id = R.string.trade),
            isSelected = state == QuotesRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(QuotesRoute)) }
        )

        DrawerItem(
            icon = R.drawable.ic_charts,
            label = "News",
            isSelected = state == ChartsRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(ChartsRoute)) }
        )

        DrawerItem(
            icon = R.drawable.six_ic_trading,
            label = "Mailbox",
            isSelected = state == TradeRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(TradeRoute)) }
        )

        DrawerItem(
            icon = R.drawable.ic_timer,
            label = "Journal",
            isSelected = state == HistoryRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(HistoryRoute)) }
        )

        DrawerItem(
            icon = R.drawable.ic_menu,
            label = "Settings",
            isSelected = state == MessagesRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(MessagesRoute)) }
        )

        DrawerItem(
            icon = R.drawable.ic_menu,
            label = "Economic calendar",
            isSelected = state == MessagesRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(MessagesRoute)) }
        )

        DrawerItem(
            icon = R.drawable.ic_menu,
            label = "Traders Community",
            isSelected = state == MessagesRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(MessagesRoute)) }
        )

        DrawerItem(
            icon = R.drawable.ic_menu,
            label = "MQL5 Algo Trading",
            isSelected = state == MessagesRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(MessagesRoute)) }
        )

        DrawerItem(
            icon = R.drawable.ic_question,
            label = "User guide",
            isSelected = state == MessagesRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(MessagesRoute)) }
        )

        DrawerItem(
            icon = R.drawable.ic_menu,
            label = "About",
            isSelected = state == MessagesRoute,
            onClick = { onAction(MainAction.DrawerItemSelected(MessagesRoute)) }
        )
    }
}

@Composable
private fun DrawerItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconColor = Color(0xFF554B49)
    val selectedBgColor = Color(0xFFF1F8FE)
    val unselectedBgColor = Color.Transparent

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(if (isSelected) selectedBgColor else unselectedBgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = iconColor
        )

        Spacer(modifier = Modifier.size(18.dp))

        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewDrawerContent() {
    AppTheme {
        DrawerContent(
            state = ChartsRoute,
            onAction = {}
        )
    }
}