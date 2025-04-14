package com.example.idea_manager

import android.app.Application
import com.example.idea_manager.domain.TaskDatabase
import com.example.idea_manager.repository.TaskRepository

class Application : Application() {
    private val database by lazy {
        TaskDatabase.getDatabase(this)
    }

    val repository by lazy {
        TaskRepository(database.taskDao())
    }
}