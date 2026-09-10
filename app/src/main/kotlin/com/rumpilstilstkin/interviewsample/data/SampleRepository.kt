package com.rumpilstilstkin.interviewsample.data

import com.rumpilstilstkin.interviewsample.domain.models.SampleItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepository @Inject constructor() {
    val items: List<SampleItem> = listOf(
        SampleItem(1, "Item1", "Some description for Item1"),
        SampleItem(2, "Item2", "Some description for Item2"),
        SampleItem(3, "Item3", "Some description for Item3"),
        SampleItem(4, "Item4", "Some description for Item4"),
        SampleItem(5, "Item5", "Some description for Item5"),
    )

    fun getItem(id: Int): SampleItem? = items.find { it.id == id }
}
