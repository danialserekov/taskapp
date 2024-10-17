package com.example.taskapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTaskName: EditText
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTaskName = findViewById(R.id.edit_task_name)
        recyclerView = findViewById(R.id.recycler_view)

        // Set up RecyclerView with a LinearLayoutManager and an adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(TaskManager.getAllTasks()) // Initialize the adapter with the task list
        recyclerView.adapter = taskAdapter

        findViewById<Button>(R.id.button_add_task).setOnClickListener {
            val taskName = editTaskName.text.toString()
            if (taskName.isNotEmpty()) {
                TaskManager.addTask(Task(taskName))
                editTaskName.text.clear()
                updateTaskList() // Update the task list in the RecyclerView
            }
        }

        findViewById<Button>(R.id.button_show_tasks).setOnClickListener {
            updateTaskList()
        }

        findViewById<Button>(R.id.button_show_completed).setOnClickListener {
            taskAdapter.updateTasks(TaskManager.getCompletedTasks()) // Update the adapter with completed tasks
        }

        findViewById<Button>(R.id.button_complete_task).setOnClickListener {
            val taskName = editTaskName.text.toString()
            if (taskName.isNotEmpty()) {
                TaskManager.completeTask(taskName)
                updateTaskList()
            }
        }

        findViewById<Button>(R.id.button_remove_task).setOnClickListener {
            val taskName = editTaskName.text.toString()
            if (taskName.isNotEmpty()) {
                TaskManager.removeTask(taskName)
                updateTaskList()
            }
        }

        findViewById<Button>(R.id.button_print_tasks).setOnClickListener {
            TaskManager.printAllTasks()
        }
    }

    private fun updateTaskList() {
        taskAdapter.updateTasks(TaskManager.getAllTasks()) // Update the adapter with the current task list
    }


// Optional: Override lifecycle methods if needed for your logic
    // Override methods only if you need to add functionality
    override fun onStart() {
        super.onStart()
        // Refresh UI or start animations
    }

    override fun onResume() {
        super.onResume()
        // Resume updates if needed
    }

    override fun onPause() {
        super.onPause()
        // Save any unsaved data if needed
    }

    override fun onStop() {
        super.onStop()
        // Release resources if needed
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clean up resources if needed
    }
}
