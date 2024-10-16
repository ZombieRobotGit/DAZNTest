package com.madbox.dazntest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.madbox.dazntest.app.composables.BottomIcon
import com.madbox.dazntest.app.screen.events.EventsScreen
import com.madbox.dazntest.app.screen.player.PlayerScreen
import com.madbox.dazntest.app.screen.schedule.ScheduleScreen
import com.madbox.dazntest.app.theme.DAZNTestTheme
import com.madbox.dazntest.app.utils.navigateAvoidingBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DAZNTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.EventsScreen.route) {
        composable(Screen.EventsScreen.route) {
            EventsScreen {
                navController.navigateAvoidingBackStack(Screen.PlayerScreen.route)
//                navigate(navController, Screen.PlayerScreen.route)
            }
        }
        composable(Screen.ScheduleScreen.route) {
            ScheduleScreen()
        }
        composable(Screen.PlayerScreen.route) {
            PlayerScreen()
        }

    }
}

@Composable
fun App() {

    val items = listOf(
        BottomNavigationData(
            title = "Events",
            route = Screen.EventsScreen.route
        ),
        BottomNavigationData(
            title = "Schedule",
            route = Screen.ScheduleScreen.route
        )
    )

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                Column {
                    Divider(thickness = 2.dp, color = Color.Black)
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BottomIcon(
                            items[0].title,
                            isSelected = currentDestination?.route == items[0].route,
                            onClick = {
                                navigate(navController, items[0].route)
                            })
                        Divider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(2.dp), color = Color.Black
                        )
                        BottomIcon(
                            items[1].title,
                            isSelected = currentDestination?.route == items[1].route,
                            onClick = {
                                navigate(navController, items[1].route)
                            })
                    }
                }
            }

        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            NavigationGraph(navController = navController)
        }
    }
}

fun navigate(navController: NavHostController, route: String) {
    navController.navigate(route) {
        // Avoid building up the backstack
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}


//@Composable
//fun RowScope.MyComposable(){
//    NavigationBarItem(selected = , onClick = { /*TODO*/ }, icon = { /*TODO*/ })
//}

data class BottomNavigationData(
    val title: String,
    val route: String
)

sealed class Screen(val route: String) {
    data object EventsScreen : Screen("events")
    data object ScheduleScreen : Screen("schedule")
    data object PlayerScreen : Screen("player")
}







