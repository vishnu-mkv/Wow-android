package com.example.wow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wow.ui.theme.WowTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var value by remember {
                        mutableStateOf("");
                    }

                    val grayColor = Color.hsl(0f, 0f, 0.99f)

                    Column {
                        Column(
                            Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Sign Up", fontSize = 30.sp, fontWeight = FontWeight.Bold);
                            Text(
                                text = "It's quick and easy.",
                                color = Color.DarkGray,
                                modifier = Modifier.padding(bottom = 10.dp)
                            );
                        }
                        Divider();
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            modifier = Modifier.padding(10.dp, 20.dp)
                        ) {

                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                OutlinedTextField(
                                    value = value, onValueChange = { value = it },
                                    placeholder = {
                                        Text("First Name");
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    shape = RoundedCornerShape(5.dp),
                                    colors = TextFieldDefaults.textFieldColors(grayColor)
                                );
                                OutlinedTextField(
                                    value = value, onValueChange = { value = it },
                                    placeholder = {
                                        Text("Last Name");
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    shape = RoundedCornerShape(5.dp),
                                    colors = TextFieldDefaults.textFieldColors(grayColor)
                                );
                            }

                            OutlinedTextField(
                                value = value, onValueChange = { value = it },
                                placeholder = {
                                    Text("Mobile Number or email address");
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = TextFieldDefaults.textFieldColors(grayColor)
                            );

                            OutlinedTextField(
                                value = value, onValueChange = { value = it },
                                placeholder = {
                                    Text("New Password");
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = TextFieldDefaults.textFieldColors(grayColor)
                            );

                            Text(
                                "By clicking Sign Up, you agree to our Terms, Privacy Policy and Cookies Policy. You may receive SMS notifications from us and can opt out at any time.",
                                fontSize = 12.sp,
                                color = Color.DarkGray
                            );
                            Text(
                                "Proceed",
                                fontSize = 12.sp,
                                color = Color.DarkGray
                            );

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    onClick = { /*TODO*/ },
                                    colors = ButtonDefaults.buttonColors(
                                        Color.hsl(
                                            114f,
                                            .65f,
                                            .36f
                                        )
                                    ),
                                    shape = RoundedCornerShape(5.dp)
                                ) {
                                    Text(
                                        "Sign Up",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium
                                    );
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
