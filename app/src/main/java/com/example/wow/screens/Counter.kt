package com.example.wow.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wow.models.CountModel


@Composable
fun Counter() {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically), horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()) {
        CounterText()
        CounterButtons()
    }
}

@Composable
fun CounterText() {
    val counterModel = viewModel<CountModel>()
    val count = counterModel.count

    Text(text = count.toString(), fontWeight = FontWeight.Bold, fontSize = 48.sp)
}

@Composable
fun CounterButtons() {
    val counterModel = viewModel<CountModel>()

    Row(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = { counterModel.decrement() }, shape = RoundedCornerShape(8.dp)) {
            Text(text = "-")
        }
        Button(onClick = { counterModel.increment() }, shape = RoundedCornerShape(8.dp)) {
            Text(text = "+")
        }
        Button(onClick = { counterModel.reset() }, shape = RoundedCornerShape(8.dp)) {
            Text(text = "Reset")
        }
    }
}