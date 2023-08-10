package com.igotfr.todo.data.repositories

import com.igotfr.todo.data.datasources.FirestoreTaskDataSource
import com.igotfr.todo.domain.models.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository {
  private val taskDataSource = FirestoreTaskDataSource()

  fun taskCreateOne(title: String, description: String, priority: Int) {
    taskDataSource.taskCreateOne(title, description, priority)
  }

  fun tasksFetchAll(): Flow<MutableList<Task>> {
    return taskDataSource.tasksFetchAll()
  }

  fun taskDeleteOne(title: String) {
    taskDataSource.taskDeleteOne(title)
  }
}