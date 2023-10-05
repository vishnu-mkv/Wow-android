package com.example.wow.screens.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.example.wow.data.AppDatabase
import com.example.wow.data.Character
import com.example.wow.data.Db
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterForm(navController: NavHostController) {

    var newCharacter by remember {
        mutableStateOf(Character())
    }
    val db = Db.getDb(LocalContext.current)

    val navigateToDetailScreen = {id:Long ->
        val toRoute = "characters/view/${id}"
        navController.navigate(toRoute)
    }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Button(onClick = { navController.popBackStack()}, shape = RoundedCornerShape(8.dp)) {
            Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "")
            Text(text = "Back")
        }
        Text(
            text = "Add Character",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = newCharacter.name,
            onValueChange = { newCharacter = newCharacter.copy(name = it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Name")
            },
        )

        TextField(
            value = newCharacter.anime,
            onValueChange = { newCharacter = newCharacter.copy(anime = it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Anime")
            },
        )

        TextField(
            value = newCharacter.year?.toString() ?: "",
            onValueChange = {
                if (it.isBlank()) newCharacter.year = null
                else if (it.isDigitsOnly()) newCharacter = newCharacter.copy(year = it.toInt())
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {
                Text(text = "Year")
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch{
                    val id = db.characterDao().create(newCharacter)
                    // navigate to detail screen but navigation can only be done on the main thread
                    launch(Dispatchers.Main) {
                        navigateToDetailScreen(id)
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Confirm")
        }
    }
}