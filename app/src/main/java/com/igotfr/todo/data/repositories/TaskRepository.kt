package com.igotfr.todo.data.repositories

import com.igotfr.todo.data.datasources.FirestoreTaskDataSource
import com.igotfr.todo.domain.models.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository {
  private val taskDataSource = FirestoreTaskDataSource()

  fun taskCreateOne(title: String, description: String, priority: Int, completed: Boolean) {
    taskDataSource.taskCreateOne(title, description, priority, completed)
  }

  fun tasksFetchAll(): Flow<MutableList<Task>> {
    return taskDataSource.tasksFetchAll()
  }

  fun taskDeleteOne(title: String) {
    taskDataSource.taskDeleteOne(title)
  }

  fun taskUpdateOneCompletedField(title: String, completed: Boolean) {
    taskDataSource.taskUpdateOneCompletedField(title, completed)
  }
}