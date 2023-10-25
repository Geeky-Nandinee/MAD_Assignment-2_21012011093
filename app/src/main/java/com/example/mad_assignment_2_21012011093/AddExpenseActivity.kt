package com.example.mad_assignment_2_21012011093

import ConfirmationSoundService
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import android.app.DatePickerDialog
import android.content.Intent
import android.media.MediaPlayer
import org.json.JSONObject
import java.util.*

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var editTextAmount: EditText
    private lateinit var editTextDate: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var editTextDescription: EditText
    private lateinit var fabAddExpense: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        // Initialize UI elements
        editTextAmount = findViewById(R.id.editTextAmount)
        editTextDate = findViewById(R.id.editTextDate)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        editTextDescription = findViewById(R.id.editTextDescription)
        fabAddExpense = findViewById(R.id.fabAddExpense)

        // Set up a click listener for the date input
        editTextDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Set up onClickListener for the Add Expense button
        fabAddExpense.setOnClickListener {
            // Get user input
            val amount = editTextAmount.text.toString()
            val date = editTextDate.text.toString()
            val category = spinnerCategory.selectedItem.toString()
            val description = editTextDescription.text.toString()

            // Convert the expense data to JSON
            val expenseJson = convertExpenseToJson(amount, date, category, description)

            // Implement code to save the expense to SharedPreferences as JSON
            saveExpenseToSharedPreferences(expenseJson)

            // Add sound feedback (replace with your sound implementation)
            playExpenseConfirmationSound()

            // Optionally, navigate back to the main screen or perform other actions
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(GregorianCalendar(year, month, dayOfMonth).time)
            editTextDate.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun convertExpenseToJson(
        amount: String,
        date: String,
        category: String,
        description: String
    ): String {
        val jsonObject = JSONObject()
        jsonObject.put("amount", amount)
        jsonObject.put("date", date)
        jsonObject.put("category", category)
        jsonObject.put("description", description)
        return jsonObject.toString()
    }

    private fun saveExpenseToSharedPreferences(expenseJson: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("ExpenseTracker", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save the JSON data to SharedPreferences
        editor.putString("expense_data", expenseJson)
        editor.apply()
    }

    private fun playExpenseConfirmationSound() {
        val serviceIntent = Intent(this, ConfirmationSoundService::class.java)
        startService(serviceIntent)

    }

}