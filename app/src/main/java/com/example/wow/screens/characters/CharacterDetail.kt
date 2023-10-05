package com.example.wow.screens.characters

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wow.data.Character
import com.example.wow.data.Db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CharacterDetail(navController: NavHostController, id: String?) {

    // get character from db
    val db = Db.getDb(LocalContext.current)

    var character by remember {
        mutableStateOf<Character?>(null)
    }

    var scope = rememberCoroutineScope()

    LaunchedEffect(key1 = "dbfetch") {
        scope.launch(Dispatchers.IO) {
            character = db.characterDao().getById(id!!.toInt())
        }
    }


    if (character == null) {
        Column {
            Text(text = "Character not found", fontWeight = FontWeight.Bold, fontSize = 32.sp)
            Text(text = "id: ${id}")
        }
        return
    }

    // in a column display name, anime, year
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Button(onClick = { navController.popBackStack() }, shape = RoundedCornerShape(8.dp)) {
            Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "")
            Text(text = "Back")
        }
        Text(text = "Character Detail", fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Name: ${character!!.name}")
        Text(text = "Anime: ${character!!.anime}")
        Text(text = "Year: ${character!!.year ?: "Unknown"}")
    }
}