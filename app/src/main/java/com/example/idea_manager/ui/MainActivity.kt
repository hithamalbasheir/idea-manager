package com.example.idea_manager.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.idea_manager.Application
import com.example.idea_manager.R
import com.example.idea_manager.TaskClickListener
import com.example.idea_manager.TaskViewModel
import com.example.idea_manager.TaskViewModelFactory
import com.example.idea_manager.domain.TaskDatabase
import com.example.idea_manager.databinding.ActivityMainBinding
import com.example.idea_manager.model.Task
import com.example.idea_manager.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), TaskClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as Application).repository)
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val repository = TaskRepository(TaskDatabase.getDatabase(this).taskDao())
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
    }

    fun loadTasks() {
        CoroutineScope(Dispatchers.IO).launch {
            val tasks = viewModel.getAllTasks()
            lifecycleScope.launch(Dispatchers.Main) {
                recyclerView.adapter = RecyclerAdapter()
            }
        }
    }

    //region Action Buttons
    override fun onTaskEditClick(task: Task) {
        TODO("Not yet implemented")
    }

    override fun onTaskDeleteClick(task: Task) {
        TODO("Not yet implemented")
    }
    //endregion
//region filters
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//endregion filters
}