package com.example.wow.screens

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet


@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
fun ConstraintLayoutExample() {
    val constraintSet = ConstraintSet {
        val buttonLogin = createRefFor("buttonLogin")
        val userNameInput = createRefFor("userNameInput")
        val passwordInput = createRefFor("passwordInput")

        constrain(userNameInput) {
            top.linkTo(parent.top, 32.dp)
            start.linkTo(parent.start, 16.dp)
            end.linkTo(parent.end, 16.dp)
        }

        constrain(passwordInput) {
            top.linkTo(userNameInput.bottom, 16.dp)
            start.linkTo(parent.start, 16.dp)
            end.linkTo(parent.end, 16.dp)
        }

        constrain(buttonLogin) {
            top.linkTo(passwordInput.bottom, 32.dp)
            start.linkTo(parent.start, 16.dp)
            end.linkTo(parent.end, 16.dp)
        }
    }

    ConstraintLayout(constraintSet, modifier= Modifier.fillMaxWidth().height(450.dp)){

        val context = LocalContext.current

        var username by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }

        TextField(
            value = username, onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth().layoutId("userNameInput"),
            placeholder = {
                Text("Enter user name")
            }
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth().layoutId("passwordInput"),
            placeholder = {
                Text("Enter password")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {Toast.makeText(context, "I can see the $password", Toast.LENGTH_LONG).show()},
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.layoutId("buttonLogin")
        ) {
            Text(text = "Sign In")
        }
    }
}