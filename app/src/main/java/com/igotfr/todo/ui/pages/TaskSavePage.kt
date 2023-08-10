package com.igotfr.todo.ui.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.igotfr.todo.constants.Constants
import com.igotfr.todo.data.repositories.TaskRepository
import com.igotfr.todo.ui.components.ButtonCustom
import com.igotfr.todo.ui.components.TextFieldCustom
import com.igotfr.todo.ui.theme.GreenDisabled
import com.igotfr.todo.ui.theme.GreenSelected
import com.igotfr.todo.ui.theme.Purple700
import com.igotfr.todo.ui.theme.RedDisabled
import com.igotfr.todo.ui.theme.RedSelected
import com.igotfr.todo.ui.theme.YellowDisabled
import com.igotfr.todo.ui.theme.YellowSelected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskSavePage(
  navController: NavController
) {
  val scope = rememberCoroutineScope()
  val context = LocalContext.current
  val taskRepository = TaskRepository()

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = "Salvar Tarefa",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple700)
      )
    }
  ) { /*contentPadding ->
    Box(modifier = Modifier.padding(contentPadding)) {*/
    var titleTask by remember { mutableStateOf("") }
    var descriptionTask by remember { mutableStateOf("") }

    var noPriorityTask by remember { mutableStateOf(false) }
    var lowPriorityTask by remember { mutableStateOf(false) }
    var mediumPriorityTask by remember { mutableStateOf(false) }
    var highPriorityTask by remember { mutableStateOf(false) }

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = it.calculateTopPadding())
        .verticalScroll(rememberScrollState())
    ) {
      TextFieldCustom(
        value = titleTask,
        onValueChange = {
          titleTask = it
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp, 20.dp, 20.dp, 0.dp),
        label = "Título Tarefa",
        maxLines = 1,
        keyboardType = KeyboardType.Text
      )
      TextFieldCustom(
        value = descriptionTask,
        onValueChange = {
          descriptionTask = it
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(150.dp)
          .padding(20.dp, 10.dp, 20.dp, 0.dp),
        label = "Descrição",
        maxLines = 5,
        keyboardType = KeyboardType.Text
      )
      Row(
        //verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = "Nível de prioridade",
          modifier = Modifier.padding(top = 12.dp)
        )
        RadioButton(
          selected = lowPriorityTask,
          onClick = {
            lowPriorityTask = !lowPriorityTask
          },
          colors = RadioButtonDefaults.colors(
            unselectedColor = GreenDisabled,
            selectedColor = GreenSelected
          )
        )
        RadioButton(
          selected = mediumPriorityTask,
          onClick = {
            mediumPriorityTask = !mediumPriorityTask
          },
          colors = RadioButtonDefaults.colors(
            unselectedColor = YellowDisabled,
            selectedColor = YellowSelected
          )
        )
        RadioButton(
          selected = highPriorityTask,
          onClick = {
            highPriorityTask = !highPriorityTask
          },
          colors = RadioButtonDefaults.colors(
            unselectedColor = RedDisabled,
            selectedColor = RedSelected
          )
        )
      }
      ButtonCustom(
        onClick = {
          var message = true

          scope.launch(Dispatchers.IO) {
            if (titleTask.isEmpty()) {
              message = false
            } else if (titleTask.isNotEmpty()) {
              if (highPriorityTask) {
                taskRepository.taskCreateOne(titleTask, descriptionTask, Constants.HIGH_PRIORITY)
              } else if (mediumPriorityTask) {
                taskRepository.taskCreateOne(titleTask, descriptionTask, Constants.MEDIUM_PRIORITY)
              } else if (lowPriorityTask) {
                taskRepository.taskCreateOne(titleTask, descriptionTask, Constants.LOW_PRIORITY)
              } else {
                taskRepository.taskCreateOne(titleTask, descriptionTask, Constants.NO_PRIORITY)
              }
              message = true
            } else if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty()) {
              if (highPriorityTask) {
                taskRepository.taskCreateOne(titleTask, descriptionTask, Constants.HIGH_PRIORITY)
              } else if (mediumPriorityTask) {
                taskRepository.taskCreateOne(titleTask, descriptionTask, Constants.MEDIUM_PRIORITY)
              } else if (lowPriorityTask) {
                taskRepository.taskCreateOne(titleTask, descriptionTask, Constants.LOW_PRIORITY)
              } else {
                taskRepository.taskCreateOne(titleTask, descriptionTask, Constants.NO_PRIORITY)
              }
              message = true
            }
          }
          scope.launch(Dispatchers.Main) {
            if (message) {
              Toast.makeText(context, "Sucesso ao salvar a tarefa!", Toast.LENGTH_SHORT).show()
              navController.popBackStack()
            } else {
              Toast.makeText(context, "Título da tarefa é obrigatório!", Toast.LENGTH_SHORT).show()
            }
          }
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(90.dp)
          .padding(20.dp),
        text = "Salvar"
      )
    }
  }
}