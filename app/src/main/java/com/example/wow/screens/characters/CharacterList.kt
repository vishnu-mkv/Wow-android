package com.example.wow.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wow.data.Db
import com.example.wow.data.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CharacterList(navController: NavController) {

    val db = Db.getDb(LocalContext.current)
    val scope = rememberCoroutineScope()
    var characters by remember {
        mutableStateOf(listOf<Character>())
    }

    LaunchedEffect("dbfetch" ) {
        scope.launch(Dispatchers.IO) {
            characters = db.characterDao().getAll()
        }
    }


    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Characters", fontWeight = FontWeight.Bold, fontSize = 32.sp)
            Button(onClick = { navController.navigate("characters/create") },
                shape = RoundedCornerShape(8.dp)
                ) {
                Text(text = "Add")
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        characters.forEach { character ->
            Row(modifier = Modifier
                .clickable(enabled = true, onClick = {
                    val toRoute = "characters/view/${character.id}"
                    navController.navigate(toRoute)
                })
                .fillMaxWidth()
                .background(Color(0xFFF0E6E6), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                , verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = character.name)
                Icon(Icons.Filled.KeyboardArrowRight, "")
            }
        }
    }
}
