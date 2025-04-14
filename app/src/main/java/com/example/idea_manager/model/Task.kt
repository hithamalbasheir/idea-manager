package com.example.idea_manager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true) val pk: Int,
    val taskName: String,
    val status: String,
    val dueDate: String,
)
