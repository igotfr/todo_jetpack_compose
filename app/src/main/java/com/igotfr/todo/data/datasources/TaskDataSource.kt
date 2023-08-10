package com.igotfr.todo.data.datasources

import com.igotfr.todo.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskDataSource {
  fun taskCreateOne(title: String, description: String, priority: Int)
  fun tasksFetchAll(): Flow<MutableList<Task>>
  fun taskDeleteOne(title: String)
}