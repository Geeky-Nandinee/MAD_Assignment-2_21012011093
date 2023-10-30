package com.example.mad_assignment_2_21012011093

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var editTextAmount: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var spinnerCategory: Spinner
    private lateinit var editTextDescription: EditText
    private lateinit var fabAddExpense: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        editTextAmount = findViewById(R.id.editTextAmount)
        datePicker = findViewById(R.id.datePicker)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        editTextDescription = findViewById(R.id.editTextDescription)
        fabAddExpense = findViewById(R.id.fabAddExpense)

        // Set up a click listener for the "Add Expense" button
        fabAddExpense.setOnClickListener {
            // Get user input
            val amount = editTextAmount.text.toString()
            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1 // Months are 0-based, so add 1
            val year = datePicker.year
            val date = "$year-$month-$day"
            val category = spinnerCategory.selectedItem.toString()
            val description = editTextDescription.text.toString()

            val expense = JSONObject()
            expense.put("amount", amount)
            expense.put("date", date)
            expense.put("category", category)
            expense.put("description", description)

            saveExpense(expense)

            navigateToTotalExpenditure()
        }

        val categories = resources.getStringArray(R.array.expense_categories)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter
    }

    private fun navigateToTotalExpenditure() {
        val intent = Intent(this, TotalExpenditureActivity::class.java)
        startActivity(intent)
    }

    private fun saveExpense(expense: JSONObject) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("ExpenseTracker", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val expensesArray = JSONArray(sharedPreferences.getString("expenses", "[]"))

        expensesArray.put(expense)

        editor.putString("expenses", expensesArray.toString())
        editor.apply()
    }
}

