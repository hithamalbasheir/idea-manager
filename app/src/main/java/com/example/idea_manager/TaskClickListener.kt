package com.example.idea_manager

import com.example.idea_manager.model.Task

interface TaskClickListener {
    fun onTaskEditClick(task: Task)
    fun onTaskDeleteClick(task: Task)
}