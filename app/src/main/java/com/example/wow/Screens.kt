package com.example.wow

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.wow.screens.BMIScreen
import com.example.wow.screens.Calculator
import com.example.wow.screens.ConstraintLayoutExample
import com.example.wow.screens.Counter
import com.example.wow.screens.FacebookLogin
import com.example.wow.screens.LiveDataDemo
import com.example.wow.screens.LoginScreen
import com.example.wow.screens.NotificationWork
import com.example.wow.screens.WorkerDemo
import com.example.wow.screens.characters.CharacterForm

data class Screen(
    val route: String,
    val label: String,
    val composable: @Composable () -> Unit
)

// Create a list of screens
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
val screens = listOf(
    Screen("home", "Home") { Home() },
    Screen("bmi", "BMI Calculator") { BMIScreen() },
    Screen("calc", "Calculator") { Calculator() },
    Screen("constrained", "Constrained") { ConstraintLayoutExample() },
    Screen("facebook", "Facebook") { FacebookLogin() },
    Screen("login", "Login") { LoginScreen() },
    Screen("notification", "Notifications", { NotificationWork() }),
    Screen("worker", "Worker", { WorkerDemo()}),
    Screen("counter", "Counter", { Counter()}),
    Screen("livedata", "Live Data", {LiveDataDemo()})
)