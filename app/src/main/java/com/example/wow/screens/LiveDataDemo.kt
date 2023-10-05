package com.example.wow.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wow.models.LiveNameModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveDataDemo() {

    val nameViewModel = viewModel<LiveNameModel>()
    val name by nameViewModel.nameData.observeAsState()

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextField(value = name ?: "", onValueChange = { nameViewModel.updateValue(it)  },
            label = { Text("Name") })

        Text(text = name ?: "")
    }
}