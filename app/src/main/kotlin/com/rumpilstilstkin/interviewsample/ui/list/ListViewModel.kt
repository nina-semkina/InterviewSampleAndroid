package com.rumpilstilstkin.interviewsample.ui.list

import androidx.lifecycle.ViewModel
import com.rumpilstilstkin.interviewsample.data.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(repository: SampleRepository) : ViewModel() {
    val items = repository.items
}
