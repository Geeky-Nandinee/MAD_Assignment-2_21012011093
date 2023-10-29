package com.example.mad_assignment_2_21012011093

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class TotalExpenditureActivity : AppCompatActivity() {
    private lateinit var radioGroupTimeFrame: RadioGroup
    private lateinit var textViewTotalExpenditure: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_expenditure)

        // Initialize UI elements
        radioGroupTimeFrame = findViewById(R.id.radioGroupTimeFrame)
        textViewTotalExpenditure = findViewById(R.id.textViewTotalExpenditure)

        // Set up radio button listeners
        radioGroupTimeFrame.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonDaily -> displayTotalExpenditure("daily")
                R.id.radioButtonWeekly -> displayTotalExpenditure("weekly")
                R.id.radioButtonMonthly -> displayTotalExpenditure("monthly")
            }
        }
    }

    private fun displayTotalExpenditure(timeFrame: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("ExpenseTracker", Context.MODE_PRIVATE)

        // Retrieve the JSON array of expenses
        val expensesArray = JSONArray(sharedPreferences.getString("expenses", "[]"))

        // Calculate and display the total expenditure based on the selected time frame
        val totalExpenditure = calculateTotalExpenditure(expensesArray, timeFrame)
        textViewTotalExpenditure.text = "Total Expenditure ($timeFrame): $totalExpenditure"
    }

    private fun calculateTotalExpenditure(expensesArray: JSONArray, timeFrame: String): Double {
        // Placeholder for calculating the total expenditure based on the selected time frame
        // Replace this with your logic to calculate the actual total expenditure
        // For simplicity, we'll sum all expenses in this example
        var total = 0.0

        for (i in 0 until expensesArray.length()) {
            val expense = expensesArray.getJSONObject(i)
            // Retrieve the date from the JSON expense object and compare it with the selected time frame
            val date = expense.getString("date")
            // Add logic to filter expenses based on the time frame
            // For demonstration, we assume all expenses are considered for the total
            total += expense.getDouble("amount")
        }

        return total
    }
}




//package com.example.mad_assignment_2_21012011093
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import org.json.JSONObject
//
//class TotalExpenditureActivity : AppCompatActivity() {
//    private lateinit var radioGroupTimeFrame: RadioGroup
//    private lateinit var radioButtonDaily: RadioButton
//    private lateinit var radioButtonWeekly: RadioButton
//    private lateinit var radioButtonMonthly: RadioButton
//    private lateinit var textViewTotalExpenditure: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_total_expenditure)
//
//        // Initialize UI elements
//        radioGroupTimeFrame = findViewById(R.id.radioGroupTimeFrame)
//        radioButtonDaily = findViewById(R.id.radioButtonDaily)
//        radioButtonWeekly = findViewById(R.id.radioButtonWeekly)
//        radioButtonMonthly = findViewById(R.id.radioButtonMonthly)
//        textViewTotalExpenditure = findViewById(R.id.textViewTotalExpenditure)
//
//        // Set up radio button listeners
//        radioGroupTimeFrame.setOnCheckedChangeListener { group, checkedId ->
//            when (checkedId) {
//                R.id.radioButtonDaily -> {
//                    // Calculate and display total daily expenditure
//                    val totalDailyExpenditure = calculateTotalExpenditureFor("daily")
//                    textViewTotalExpenditure.text = "Total Expenditure (Daily): $totalDailyExpenditure"
//                }
//                R.id.radioButtonWeekly -> {
//                    // Calculate and display total weekly expenditure
//                    val totalWeeklyExpenditure = calculateTotalExpenditureFor("weekly")
//                    textViewTotalExpenditure.text = "Total Expenditure (Weekly): $totalWeeklyExpenditure"
//                }
//                R.id.radioButtonMonthly -> {
//                    // Calculate and display total monthly expenditure
//                    val totalMonthlyExpenditure = calculateTotalExpenditureFor("monthly")
//                    textViewTotalExpenditure.text = "Total Expenditure (Monthly): $totalMonthlyExpenditure"
//                }
//            }
//        }
//
//        // Load and display the JSON expense data
//        val expenseJson = loadExpenseFromSharedPreferences()
//        displayExpenseData(expenseJson)
//    }
//
//    private fun calculateTotalExpenditureFor(timeFrame: String): Double {
//        // Placeholder for calculating the total expenditure based on the selected time frame
//        // You should replace this with your logic to calculate the actual total expenditure
//        // Sample: Calculate a random total expenditure
//        return when (timeFrame) {
//            "daily" -> 75.0
//            "weekly" -> 450.0
//            "monthly" -> 1900.0
//            else -> 0.0
//        }
//    }
//
//    private fun loadExpenseFromSharedPreferences(): String? {
//        val sharedPreferences: SharedPreferences =
//            getSharedPreferences("ExpenseTracker", Context.MODE_PRIVATE)
//
//        // Load the JSON expense data from SharedPreferences
//        return sharedPreferences.getString("expense_data", null)
//    }
//
//    private fun displayExpenseData(expenseJson: String?) {
//        if (expenseJson != null) {
//            // Parse the JSON and extract data for display
//            val jsonObject = JSONObject(expenseJson)
//            val amount = jsonObject.getString("amount")
//            val date = jsonObject.getString("date")
//            val category = jsonObject.getString("category")
//            val description = jsonObject.getString("description")
//
//            // Display the data in a TextView or other UI elements
//            textViewTotalExpenditure.text = "Amount: $amount\nDate: $date\nCategory: $category\nDescription: $description"
//        } else {
//            // Handle the case where there is no expense data to display
//            textViewTotalExpenditure.text = "No expense data available"
//        }
//    }
//}
//
