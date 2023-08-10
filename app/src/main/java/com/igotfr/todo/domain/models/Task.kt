package com.igotfr.todo.domain.models

data class Task(
  val title: String = "",
  val description: String? = null,
  val priority: Int? = null
)
