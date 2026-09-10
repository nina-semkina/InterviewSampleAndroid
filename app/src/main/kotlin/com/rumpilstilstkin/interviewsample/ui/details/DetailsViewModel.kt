package com.rumpilstilstkin.interviewsample.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.rumpilstilstkin.interviewsample.data.SampleRepository
import com.rumpilstilstkin.interviewsample.ui.navigation.SamplesScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: SampleRepository,
) : ViewModel() {
    val item = repository.getItem(savedStateHandle.toRoute<SamplesScreen.DetailsRoute>().itemId)
}
