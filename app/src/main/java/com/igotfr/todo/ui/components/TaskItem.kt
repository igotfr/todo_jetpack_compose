package com.igotfr.todo.ui.components

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.igotfr.todo.R
import com.igotfr.todo.data.repositories.TaskRepository
import com.igotfr.todo.domain.models.Task
import com.igotfr.todo.ui.theme.CardPriorityShapes
import com.igotfr.todo.ui.theme.GreenSelected
import com.igotfr.todo.ui.theme.RedSelected
import com.igotfr.todo.ui.theme.YellowSelected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TaskItem(
  position: Int,
  tasks: MutableList<Task>,
  context: Context,
  navController: NavController
) {
  val titleTask = tasks[position].title
  val descriptionTask = tasks[position].description
  val priorityTask = tasks[position].priority
  val completedTask = tasks[position].completed

  var completedTaskState by remember { mutableStateOf(completedTask) }

  val scope = rememberCoroutineScope()
  val taskRepository = TaskRepository()

  fun dialogDelete() {
    val alertDialog = AlertDialog.Builder(context)
    alertDialog.setTitle("Deletar Tarefa")
      .setMessage("Deseja excluir a tarefa?")
      .setPositiveButton("Sim") { _, _ ->
        taskRepository.taskDeleteOne(titleTask)
        scope.launch(Dispatchers.Main) {
          tasks.removeAt(position)
          navController.navigate("tasks")
          Toast.makeText(context, "Sucesso ao deletar tarefa!", Toast.LENGTH_SHORT).show()
        }
      }
      .setNegativeButton("Não") { _, _ ->

      }
      .show()
  }

  fun toggleCompleted() {
    taskRepository.taskUpdateOneCompletedField(titleTask, !completedTaskState)
    scope.launch(Dispatchers.Main) {
      completedTaskState = !completedTaskState
      //tasks[position].apply { completed = mutableStateOf(!completedTask.value) }
      //completedTaskState = !completedTask
      //navController.navigate("tasks")
    }
  }

  val priorityLevel: String = when (priorityTask) {
    0 -> "Sem prioridade"
    1 -> "Prioridade Baixa"
    2 -> "Prioridade Média"
    else -> "Prioridade Alta"
  }

  val priorityColor = when (priorityTask) {
    0 -> Color.Black
    1 -> GreenSelected
    2 -> YellowSelected
    else -> RedSelected
  }

  Card(
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    modifier = Modifier
      .fillMaxWidth()
      .padding(10.dp)
  ) {
    Column(
      modifier = Modifier.padding(20.dp)
    ) {
      Text(
        text = titleTask,
        modifier = Modifier.padding(top = 10.dp, start = 10.dp)
      )
      Text(
        text = descriptionTask.toString(),
        modifier = Modifier.padding(top = 10.dp, start = 10.dp)
      )
      Row(
        modifier = Modifier.padding(top = 10.dp, start = 10.dp, bottom = 10.dp)
      ) {
        Checkbox(
          checked = completedTaskState,
          onCheckedChange = {
            toggleCompleted()
          }
        )
        Text(
          text = priorityLevel,
          modifier = Modifier.padding(top = 15.dp, end = 10.dp)
        )
        Card(
          colors = CardDefaults.cardColors(
            containerColor = priorityColor
          ),
          modifier = Modifier
            .size(30.dp)
            .offset(y = 10.dp),
          shape = CardPriorityShapes.large
        ) {}
        IconButton(
          onClick = {
            dialogDelete()
          },
          modifier = Modifier.padding(start = 30.dp)
        ) {
          Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete_24),
            contentDescription = "delete this task"
          )
        }
      }
    }
    /*ConstraintLayout(
      modifier = Modifier.padding(20.dp)
    ) {
      val (txtTitle, txtDescription, cardPriority, txtPriority, btnDelete) = createRefs()

      Text(
        text = titleTask,
        modifier = Modifier.constrainAs(txtTitle) {
          top.linkTo(parent.top, margin = 10.dp)
          start.linkTo(parent.start, margin = 10.dp)
        }
      )
      Text(
        text = descriptionTask.toString(),
        modifier = Modifier.constrainAs(txtDescription) {
          top.linkTo(txtTitle.bottom, margin = 10.dp)
          start.linkTo(parent.start, margin = 10.dp)
        }
      )
      Text(
        text = priorityLevel,
        modifier = Modifier.constrainAs(txtPriority) {
          top.linkTo(txtDescription.bottom, margin = 10.dp)
          start.linkTo(parent.start, margin = 10.dp)
          bottom.linkTo(parent.bottom, margin = 10.dp)
        }
      )
      Card(
        colors = CardDefaults.cardColors(
          containerColor = priorityColor
        ),
        modifier = Modifier
          .size(30.dp)
          .constrainAs(cardPriority) {
            top.linkTo(txtDescription.bottom, margin = 10.dp)
            start.linkTo(txtPriority.end, margin = 10.dp)
            bottom.linkTo(parent.bottom, margin = 10.dp)
          },
        shape = CardPriorityShapes.large
      ) {

      }
      IconButton(
        onClick = {},
        modifier = Modifier.constrainAs(btnDelete) {
          top.linkTo(txtDescription.bottom, margin = 10.dp)
          start.linkTo(cardPriority.end, margin = 30.dp)
          end.linkTo(parent.end, margin = 10.dp)
          bottom.linkTo(parent.bottom, margin = 10.dp)
        }
      ) {
        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete_24), contentDescription = "delete this task")
      }
    }*/
  }
}

/*@Composable
@Preview
private fun ItemTaskPreview() {
  ItemTask()
}*/