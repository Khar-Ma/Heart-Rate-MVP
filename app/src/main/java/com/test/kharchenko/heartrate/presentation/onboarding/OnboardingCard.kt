package com.test.kharchenko.heartrate.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.test.kharchenko.heartrate.R

data class OnboardingCard(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val text: Int,
)

val onboardingCardsList = listOf(
    OnboardingCard(
        R.drawable.img_onboarding1,
        R.string.onboarding1_title,
        R.string.onboarding1_text
    ),
    OnboardingCard(
        R.drawable.img_onboarding2,
        R.string.onboarding2_title,
        R.string.onboarding2_text
    ),
    OnboardingCard(
        R.drawable.img_onboarding3,
        R.string.onboarding3_title,
        R.string.onboarding3_text
    ),
)