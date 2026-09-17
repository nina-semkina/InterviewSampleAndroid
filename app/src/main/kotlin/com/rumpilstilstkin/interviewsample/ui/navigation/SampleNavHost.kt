package com.rumpilstilstkin.interviewsample.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rumpilstilstkin.interviewsample.ui.details.DetailsScreen
import com.rumpilstilstkin.interviewsample.ui.details.DetailsViewModel
import com.rumpilstilstkin.interviewsample.ui.list.ListScreen

@Composable
fun SampleNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = SamplesScreen.ListRoute,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        popEnterTransition = { fadeIn(animationSpec = tween(300)) },
        popExitTransition = { fadeOut(animationSpec = tween(300)) },
    ) {
        composable<SamplesScreen.ListRoute> {
            ListScreen()
        }
        composable<SamplesScreen.DetailsRoute> {
            val viewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(
                item = viewModel.item,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
