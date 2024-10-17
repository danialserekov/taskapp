package com.example.taskapp

object TaskManager {
    private val taskList = mutableListOf<Task>()

    // Simple function to add a task
    fun addTask(task: Task) {
        taskList.add(task)
        println("Task added: ${task.name}")
    }

    // Simple function to retrieve all tasks
    fun getAllTasks(): List<Task> {
        return taskList
    }

    // Complete a task by its name using a lambda
    fun completeTask(taskName: String) {
        taskList.find { it.name == taskName }?.apply {
            isCompleted = true
            println("Task completed: $taskName")
        } ?: println("Task not found: $taskName")
    }

    // Get completed tasks using a lambda
    fun getCompletedTasks(): List<Task> {
        return taskList.filter { it.isCompleted }
    }

    // Remove a task using a simple function
    fun removeTask(taskName: String) {
        val wasRemoved = taskList.removeIf { it.name == taskName }
        if (wasRemoved) {
            println("Task removed: $taskName")
        } else {
            println("Task not found: $taskName")
        }
    }

    // Print all tasks using a lambda
    fun printAllTasks() {
        taskList.forEach { task ->
            println("Task: ${task.name}, Completed: ${task.isCompleted}")
        }
    }
}
