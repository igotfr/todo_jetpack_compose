package com.igotfr.todo.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.igotfr.todo.R
import com.igotfr.todo.data.repositories.TaskRepository
//import com.igotfr.todo.domain.models.Task
import com.igotfr.todo.ui.components.TaskItem
import com.igotfr.todo.ui.theme.Purple700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksPage(
  navController: NavController
) {
  val taskRepository = TaskRepository()
  val context = LocalContext.current

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = "Lista de Tarefas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple700)
      )
    },
    containerColor = Color.Black,
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          navController.navigate("taskSave")
        },
        containerColor = Purple700
      ) {
        Image(
          imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_24),
          contentDescription = "Ícone de salvar tarefa"
        )
      }
    }
  ) {
    /*val tasks: MutableList<Task> = mutableListOf(
      Task(title = "Jogar futebol", description = "adsjfjsdfjdfnhdf", priority = 0),
      Task(title = "Ir ao cinema", description = "adsjfjsdfjdfnhdf", priority = 1),
      Task(title = "Ir para a faculdade", description = "adsjfjsdfjdfnhdf", priority = 2),
      Task(title = "Tarefa 4", description = "adsjfjsdfjdfnhdf", priority = 3)
    )*/

    val tasks = taskRepository.tasksFetchAll().collectAsState(mutableListOf()).value
    
    LazyColumn(
      modifier = Modifier.padding(top = it.calculateTopPadding())
    ) {
      itemsIndexed(tasks) {position, _ ->
        TaskItem(position, tasks, context, navController)
      }
    }
  }
}

/*@Preview()
@Composable()
fun TasksPreview() {
  Tasks()
}*/