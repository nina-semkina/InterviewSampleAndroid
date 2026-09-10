package com.rumpilstilstkin.interviewsample.ui.navigation

import kotlinx.serialization.Serializable

sealed interface SamplesScreen {
    @Serializable
    data object ListRoute : SamplesScreen

    @Serializable
    data class DetailsRoute(val itemId: Int) : SamplesScreen
}
