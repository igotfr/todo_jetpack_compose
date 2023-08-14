package com.igotfr.todo.domain.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Task(
  val title: String = "",
  val description: String? = null,
  val priority: Int? = null,
  var completed: Boolean = false
)
