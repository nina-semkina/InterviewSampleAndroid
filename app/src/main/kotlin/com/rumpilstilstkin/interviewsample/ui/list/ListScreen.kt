package com.rumpilstilstkin.interviewsample.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rumpilstilstkin.interviewsample.domain.models.SampleItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(items: List<SampleItem>, onItemClick: (Int) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Список") }) }) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items, key = { it.id }) { item ->
                Card(onClick = { onItemClick(item.id) }, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(20.dp),
                    )
                }
            }
        }
    }
}
