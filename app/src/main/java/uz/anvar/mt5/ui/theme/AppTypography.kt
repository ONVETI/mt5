package uz.anvar.mt5.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.anvar.mt5.R

@Composable
fun montserratFontFamily() = FontFamily(
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
)

@Composable
fun interFontFamily() = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
)

data class AppTypographyPalette(
    val primary32Semibold: TextStyle,
    val primary28Semibold: TextStyle,
    val primary24Semibold: TextStyle,
    val primary18Semibold: TextStyle,
    val primary16Medium: TextStyle,
    val primary14SemiboldCaps: TextStyle,

    val secondary24Medium: TextStyle,
    val secondary20Regular: TextStyle,
    val secondary18Regular: TextStyle,
    val secondary18Medium: TextStyle,
    val secondary18Semibold: TextStyle,
    val secondary16Regular: TextStyle,
    val secondary16Medium: TextStyle,
    val secondary14Regular: TextStyle,
    val secondary14Medium: TextStyle,
    val secondary12SemiboldCaps: TextStyle,
)

@Composable
fun createAppTypographyPalette(): AppTypographyPalette {
    val montserrat = montserratFontFamily()
    val inter = interFontFamily()
    return AppTypographyPalette(
        primary32Semibold = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
            lineHeight = 39.sp,
            letterSpacing = (-0.02).sp,
        ),
        primary28Semibold = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 32.sp,
            letterSpacing = (-0.02).sp,
        ),
        primary24Semibold = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = (-0.02).sp,
        ),
        primary18Semibold = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 22.sp,
        ),
        primary16Medium = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.02).sp,
        ),
        primary14SemiboldCaps = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.01.sp,
        ),

        secondary20Regular = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 28.sp,
        ),

        secondary24Medium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 32.sp,
        ),
        secondary18Semibold = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 22.sp,
        ),
        secondary18Regular = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 22.sp,
        ),
        secondary18Medium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 24.sp,
        ),
        secondary16Regular = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.sp,
        ),
        secondary16Medium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 22.sp,
        ),
        secondary14Regular = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        ),
        secondary14Medium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        ),
        secondary12SemiboldCaps = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.02.sp,
        ),
    )
}

internal val LocalAppTypography = staticCompositionLocalOf<AppTypographyPalette?> { null }