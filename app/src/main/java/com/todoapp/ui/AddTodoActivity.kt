package com.todoapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.todoapp.R
import com.todoapp.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        // Initialize views
        titleEditText = findViewById(R.id.edittext_todo_title)
        descriptionEditText = findViewById(R.id.edittext_todo_description)
        saveButton = findViewById(R.id.button_save_todo)

        // Initialize ViewModel
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        // Set up save button click listener
        saveButton.setOnClickListener {
            saveTodo()
        }
    }

    private fun saveTodo() {
        val title = titleEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()

        // Validate inputs
        if (title.isEmpty()) {
            titleEditText.error = "Title is required"
            return
        }

        // Attempt to save todo
        try {
            todoViewModel.addTodo(title, description)
            Toast.makeText(this, "Todo item added successfully", Toast.LENGTH_SHORT).show()
            finish() // Close the activity after saving
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to save todo: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}