package com.example.wow.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator() {

    var a by remember {
        mutableStateOf("")
    }

    var b by remember {
        mutableStateOf("")
    }

    var c by remember {
        mutableStateOf(0f)
    }

    Column(verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(
            value = a, onValueChange = { a = it },
            placeholder = {
                Text("Num 1")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),

            )
        OutlinedTextField(
            value = b, onValueChange = { b = it },
            placeholder = {
                Text("Num 2")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),

            )

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { c = a.toFloat() + b.toFloat() }) {
                Text(text = "+")
            }

            Button(onClick = { c = a.toFloat() - b.toFloat() }) {
                Text(text = "-")
            }

            Button(onClick = { c = a.toFloat() * b.toFloat() }) {
                Text(text = "*")
            }

            Button(onClick = { c = a.toFloat() / b.toFloat() }) {
                Text(text = "/")
            }
        }

        Text(text = c.toString())
    }

}
