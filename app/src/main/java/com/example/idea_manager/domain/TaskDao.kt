package com.example.idea_manager.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.idea_manager.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): Flow<List<Task>>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table WHERE status = :status")
    fun getTasksByStatus(status: String): Flow<List<Task>>
}
