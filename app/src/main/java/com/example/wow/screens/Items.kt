package com.example.wow.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun Items() {
    val list by remember { mutableStateOf(listOf("A","B","C")) }

    LazyColumn() {
        itemsIndexed(list) {index, value ->
            Text(text = "${index+1}. $value", modifier = Modifier.animateItemPlacement())
        }
    }
}