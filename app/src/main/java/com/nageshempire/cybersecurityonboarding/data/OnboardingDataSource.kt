package com.nageshempire.cybersecurityonboarding.data

import com.nageshempire.cybersecurityonboarding.R
import com.nageshempire.cybersecurityonboarding.domain.OnboardingSlideContent

object OnboardingDataSource {
    val slides = listOf(
        OnboardingSlideContent(
            R.string.onboard_title_1,
            R.string.onboard_desc_1,
            R.drawable.ic_secure_server
        ),
        OnboardingSlideContent(
            R.string.onboard_title_2,
            R.string.onboard_desc_2,
            R.drawable.ic_growth_analytics
        ),
        OnboardingSlideContent(
            R.string.onboard_title_3,
            R.string.onboard_desc_3,
            R.drawable.ic_educator
        )
    )
}