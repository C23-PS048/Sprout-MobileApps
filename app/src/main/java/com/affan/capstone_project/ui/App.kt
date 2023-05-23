package com.affan.capstone_project.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.affan.capstone_project.ui.component.navigation.BottomBar

import com.affan.capstone_project.ui.component.navigation.NavItem
import com.affan.capstone_project.ui.screen.ForumScreen
import com.affan.capstone_project.ui.screen.HomeScreen
import com.affan.capstone_project.ui.screen.Screen
import com.affan.capstone_project.ui.theme.GreenLight
import com.affan.capstone_project.ui.theme.GreenMed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {

            NavigationBar(navController = navController)

        }, modifier = modifier
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {


            composable(Screen.Home.route) {
                HomeScreen()
            }

            composable(Screen.Forum.route) {
                ForumScreen()
            }

        }
    }
}


@Composable
fun NavigationBar(
    navController: NavController, modifier: Modifier = Modifier
) {
    BottomBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navItems = listOf(
            NavItem(
                name = "HOME",
                icon = Icons.Default.Home,
                route = Screen.Home
            ), NavItem(
                name = "HOME",
                icon = Icons.Default.Home,
                route = Screen.Forum
            ),
        )
        BottomBar() {
            navItems.map { item ->
                NavigationBarItem(icon = {
                    Icon(imageVector = item.icon, contentDescription = item.name)
                },
                    label = { Text(text = item.name) },
                    selected = currentRoute == item.route.route,
                    onClick = {
                        navController.navigate(item.route.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.White,
                    selectedIconColor = GreenMed,
                    selectedTextColor = GreenMed,
                    unselectedIconColor = GreenLight),
                alwaysShowLabel = false)
            }
        }
    }
}