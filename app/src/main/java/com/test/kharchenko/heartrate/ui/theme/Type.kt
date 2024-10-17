package com.test.kharchenko.heartrate.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontWeight
import com.test.kharchenko.heartrate.R
/*
internal val RubikFamily = FontFamily(
    Font(R.font.rubik_bold, FontWeight.Bold, loadingStrategy = FontLoadingStrategy.OptionalLocal),
    Font(R.font.rubik_semibold, FontWeight.SemiBold, loadingStrategy = FontLoadingStrategy.OptionalLocal),
    Font(R.font.rubik_medium, FontWeight.Medium, loadingStrategy = FontLoadingStrategy.OptionalLocal),
    Font(R.font.rubik_regular, FontWeight.Normal, loadingStrategy = FontLoadingStrategy.OptionalLocal),
)*/
internal val RubikFamily = FontFamily(
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_semibold, FontWeight.SemiBold),
    Font(R.font.rubik_medium, FontWeight.Medium),
    Font(R.font.rubik_regular, FontWeight.Normal),
)
val baseline = Typography()
val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Bold),
    displayMedium = baseline.displayMedium.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Bold),
    displaySmall = baseline.displaySmall.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Bold),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = RubikFamily, fontWeight = FontWeight.SemiBold),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = RubikFamily, fontWeight = FontWeight.SemiBold),
    headlineSmall =baseline.headlineSmall.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Medium),
    titleLarge = baseline.titleLarge.copy(fontFamily = RubikFamily, fontWeight = FontWeight.SemiBold),
    titleMedium = baseline.titleMedium.copy(fontFamily = RubikFamily, fontWeight = FontWeight.SemiBold),
    titleSmall = baseline.titleSmall.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Medium),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Medium),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Normal),
    bodySmall = baseline.bodySmall.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Normal),
    labelLarge = baseline.labelLarge.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Medium),
    labelMedium = baseline.labelMedium.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Normal),
    labelSmall =baseline.labelSmall.copy(fontFamily = RubikFamily, fontWeight = FontWeight.Normal)
)