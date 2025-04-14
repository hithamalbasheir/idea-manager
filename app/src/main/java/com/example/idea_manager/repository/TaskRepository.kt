package com.example.idea_manager.repository

import com.example.idea_manager.domain.TaskDao
import com.example.idea_manager.model.Task
import kotlinx.coroutines.flow.Flow


class TaskRepository(private val taskDao: TaskDao) {
    suspend fun insertTask(task: Task) {
        taskDao.insert(task)
    }

    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }

    fun getTasksByStatus(status: String): Flow<List<Task>> {
        return taskDao.getTasksByStatus(status)
    }
}
