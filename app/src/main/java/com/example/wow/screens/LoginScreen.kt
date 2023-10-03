package com.example.wow.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wow.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),
        modifier = Modifier.padding(10.dp),
    ) {
        var username by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }

        Image(
            painter = painterResource(id = R.drawable.tree),
            contentDescription = "this is an image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,

            )

        Text(
            text = "WoW",
            fontSize = 22.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth(),
        )

        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(text = "User Name")
            TextField(
                value = username, onValueChange = { username = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Enter user name")
                }
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(text = "Password")
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Enter password")
                },
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(text = "Sign In")
            }
        }
    }
}

