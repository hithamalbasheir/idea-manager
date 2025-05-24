package com.example.idea_manager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.idea_manager.TaskClickListener
import com.example.idea_manager.databinding.ListRowBinding
import com.example.idea_manager.model.Task

class RecyclerAdapter(
    initialTasks: List<Task>, // Changed parameter name for clarity
    private val clickListener: TaskClickListener
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var taskList: MutableList<Task> = initialTasks.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskName.text = taskList[position].taskName
        holder.status.text = taskList[position].status
        holder.dueDate.text = taskList[position].dueDate
        holder.deleteButton.setOnClickListener {
            (holder.itemView.context as TaskClickListener).onTaskDeleteClick(taskList[position])
        }
        holder.editButton.setOnClickListener {
            (holder.itemView.context as TaskClickListener).onTaskEditClick(taskList[position])
        }
    }

    fun submitList(newTasks: List<Task>) {
        taskList.clear()
        taskList.addAll(newTasks)
        notifyDataSetChanged()
    }

    class ViewHolder(binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val taskName = binding.taskName
        val status = binding.status
        val dueDate = binding.dueDate
        val deleteButton = binding.deleteTaskBtn
        val editButton = binding.editTaskBtn
    }
}