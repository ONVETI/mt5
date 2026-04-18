package uz.anvar.mt5.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewHorizontalDivider() {
    DottedDivider(
        modifier = Modifier.padding(vertical = 12.dp)
    )
}

@Composable
fun DottedDivider(
    modifier: Modifier = Modifier,
    dotRadius: Dp = 2.dp,
    dotSpacing: Dp = 6.dp,
    color: Color = Color(0xFFE7E7E7),
) {
    val dotRadiusPx = with(LocalDensity.current) { dotRadius.toPx() }
    val dotSpacingPx = with(LocalDensity.current) { dotSpacing.toPx() }

    Canvas(
        modifier = modifier.height(dotRadius * 2)
    ) {
        val y = size.height / 2f
        var x = dotRadiusPx

        while (x < size.width) {
            drawCircle(
                color = color,
                radius = dotRadiusPx,
                center = Offset(x, y)
            )
            x += dotRadiusPx * 2 + dotSpacingPx
        }
    }
}