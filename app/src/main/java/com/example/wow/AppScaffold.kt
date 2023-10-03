package com.example.wow

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()

    val toggleMenu = {
        scope.launch {
            drawerState.apply {
                if (drawerState.isClosed) open() else close()
            }
        }
    }

    val navigate = {path:String ->
        run{
            scope.launch {
                drawerState.apply {
                    close()
                }
            }
            navController.navigate(path) {
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Wow", modifier = Modifier.padding(16.dp))
                Divider()

                screens.forEach{screen ->
                    NavigationDrawerItem(
                        label = { Text(text = screen.label) },
                        selected = false,
                        onClick = { navigate(screen.route) }
                    )
                }

            }
        }
    ) {
        Scaffold(
            topBar = { AppTopBar(toggleMenu) },
            content = {
                NavHost(navController = navController,
                    startDestination = "home",
                    modifier = Modifier.padding(it)) {
                    screens.forEach { screen ->
                        composable(route = screen.route) {
                            screen.composable()
                        }
                    }
            }
        },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar( toggleMenu: () -> Job) {
    TopAppBar(title = { Text("Wow") }, navigationIcon = { IconButton(onClick = {
        toggleMenu()
    }) {
        Icon(Icons.Filled.Menu, "menu")
    }}
    )
}