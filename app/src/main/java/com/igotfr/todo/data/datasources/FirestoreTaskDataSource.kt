package com.igotfr.todo.data.datasources

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.igotfr.todo.domain.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FirestoreTaskDataSource : TaskDataSource {
  private val db = FirebaseFirestore.getInstance()
  private val tasksAllMutable = MutableStateFlow<MutableList<Task>>(mutableListOf())
  private val tasksAll: StateFlow<MutableList<Task>> = tasksAllMutable

  override fun taskCreateOne(title: String, description: String, priority: Int, completed: Boolean) {
    val taskMap = hashMapOf(
      "title" to title,
      "description" to description,
      "priority" to priority,
      "completed" to completed
    )

    db.collection("tasks").document(title).set(taskMap).addOnCompleteListener {}
      .addOnFailureListener { exception ->
        Log.e(TAG, "Falha da molestia", exception)
      }
  }

  override fun tasksFetchAll(): Flow<MutableList<Task>> {
    val tasks: MutableList<Task> = mutableListOf()

    db.collection("tasks").get()
      /*.addOnCompleteListener { querySnapshot ->
        if (querySnapshot.isSuccessful) {
          for (document in querySnapshot.result) {
            val task = document.toObject(Task::class.java)
            tasks.add(task)
            tasksAllMutable.value = tasks
          }
        }
      }*/
      .addOnSuccessListener { result ->
        for (document in result) {
          //val task = document.toObject(Task::class.java)
          val task = document.toObject<Task>()
          tasks.add(task)
          tasksAllMutable.value = tasks
        }
      }
      .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents.", exception)
      }

    return tasksAll
  }

  override fun taskDeleteOne(title: String) {
    db.collection("tasks").document(title).delete().addOnCompleteListener {

    }.addOnFailureListener {

    }
  }

  override fun taskUpdateOneCompletedField(title: String, completed: Boolean) {
    db.collection("tasks").document(title).update("completed", completed)
  }
}