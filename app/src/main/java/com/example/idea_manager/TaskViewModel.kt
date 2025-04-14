package com.example.idea_manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.idea_manager.model.Task
import com.example.idea_manager.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel (
    private val repository: TaskRepository
) : ViewModel() {


    fun getAllTasks() : Flow<List<Task>> {
        return repository.getAllTasks()
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun getTasksByStatus(status: String): Flow<List<Task>> {
        return repository.getTasksByStatus(status)
    }


}

class TaskViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}