package com.flexfitnesstestapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.flexfitnesstestapp.R

val nunitoFamily = FontFamily(
    Font(R.font.nunito_font_light, FontWeight.Thin),
    Font(R.font.nunito_font_regular, FontWeight.Normal),
    Font(R.font.nunito_font_bold, FontWeight.Bold),
    Font(R.font.nunito_font_extra_bold, FontWeight.ExtraBold),

    // If you have a font like bold italic, you can add it like that
    //Font(R.font.MY_BOLD_ITALIC_FONT_NAME, FontWeight.Bold, FontStyle.Italic),
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        textAlign = TextAlign.Center
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        textAlign = TextAlign.Center
    ),
    titleLarge = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 50.sp,
        lineHeight = 52.sp,
        letterSpacing = 1.sp,
        textAlign = TextAlign.Center
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)