package com.example.wow.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun calculateBMI(height: Float, weight: Float): Float {
    return weight / (height * height)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun BMIScreen() {

    var height by remember {
        mutableStateOf("")
    }

    var weight by remember {
        mutableStateOf("")
    }

    var BMI by remember {
        mutableStateOf(0f)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            "BMI Calculator",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = height, onValueChange = { height = it },
            placeholder = {
                Text("Enter weight in KGs")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
        )
        OutlinedTextField(
            value = weight, onValueChange = { weight = it },
            placeholder = {
                Text("Enter Height in meters")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
        )

        Button(
            onClick = { BMI = calculateBMI(height.toFloat(), weight.toFloat()) },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calculate")
        }

        Text(text = "Your BMI is $BMI")
    }


}
