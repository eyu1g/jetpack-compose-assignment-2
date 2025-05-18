package com.example.jetpackcomposeassignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.jetpackcomposeassignment2.model.Todo
import com.example.jetpackcomposeassignment2.repository.TodoRepository
import com.example.jetpackcomposeassignment2.ui.screens.*
import com.example.jetpackcomposeassignment2.ui.theme.JetpackComposeAssignment2Theme
import com.example.jetpackcomposeassignment2.viewmodel.TodoListViewModel
import com.google.gson.Gson
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = TodoRepository(applicationContext)

        setContent {
            JetpackComposeAssignment2Theme {
                val navController = rememberNavController()
                val viewModel = remember { TodoListViewModel(repository) }

                NavHost(navController = navController, startDestination = "welcome") {

                    // 👋 Welcome Screen
                    composable("welcome") {
                        WelcomeScreen(
                            onContinueClick = {
                                navController.navigate("list") {
                                    popUpTo("welcome") { inclusive = true } // Remove welcome from backstack
                                }
                            }
                        )
                    }

                    // 📋 Todo List Screen
                    composable("list") {
                        val uiState by viewModel.uiState.collectAsState()
                        TodoListScreen(
                            uiState = uiState,
                            onItemClick = { todo ->
                                val todoJson = Gson().toJson(todo)
                                navController.navigate("detail/$todoJson")
                            }
                        )
                    }

                    // 🔍 Todo Detail Screen
                    composable(
                        route = "detail/{todo}",
                        arguments = listOf(navArgument("todo") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val todoJson = backStackEntry.arguments?.getString("todo")
                        val todo = Gson().fromJson(todoJson, Todo::class.java)

                        TodoDetailScreen(
                            todo = todo,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
