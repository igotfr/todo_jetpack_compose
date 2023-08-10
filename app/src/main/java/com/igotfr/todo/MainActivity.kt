package com.igotfr.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igotfr.todo.ui.pages.TaskSavePage
import com.igotfr.todo.ui.pages.TasksPage
import com.igotfr.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TodoTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "tasks") {
          composable(route = "tasks") {
            TasksPage(navController)
          }
          composable(route = "taskSave") {
            TaskSavePage(navController)
          }
        }
      }
    }
  }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Button(onClick = {  },
    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
    Text(
      text = "Hello $name!",
      modifier = modifier
    )
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  TodoTheme {
    Greeting("Android")
  }
}*/