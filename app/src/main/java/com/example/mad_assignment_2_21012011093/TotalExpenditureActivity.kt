package com.example.mad_assignment_2_21012011093

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*

class TotalExpenditureActivity : AppCompatActivity() {
    private lateinit var radioGroupTimeFrame: RadioGroup
    private lateinit var textViewTotalExpenditure: TextView
    private lateinit var listViewExpenses: ListView
    private lateinit var expensesList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_expenditure)

        radioGroupTimeFrame = findViewById(R.id.radioGroupTimeFrame)
        textViewTotalExpenditure = findViewById(R.id.textViewTotalExpenditure)
        listViewExpenses = findViewById(R.id.listViewExpenses)

        expensesList = mutableListOf()

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


        val expensesArray = JSONArray(sharedPreferences.getString("expenses", "[]"))

        expensesList.clear()

        val totalExpenditure = calculateTotalExpenditure(expensesArray, timeFrame)

        for (i in 0 until expensesArray.length()) {
            val expense = expensesArray.getJSONObject(i)
            val date = expense.getString("date")

            if (isExpenseInTimeFrame(date, timeFrame)) {
                val amount = expense.getString("amount")
                val category = expense.getString("category")
                val description = expense.getString("description")
                val expenseString = "Date: $date\nAmount: $amount\nCategory: $category\nDescription: $description"
                expensesList.add(expenseString)
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, expensesList)
        listViewExpenses.adapter = adapter

        textViewTotalExpenditure.text = "Total Expenditure ($timeFrame): $totalExpenditure"
    }

    private fun calculateTotalExpenditure(expensesArray: JSONArray, timeFrame: String): Double {
        var total = 0.0

        for (i in 0 until expensesArray.length()) {
            val expense = expensesArray.getJSONObject(i)
            val date = expense.getString("date")

            if (isExpenseInTimeFrame(date, timeFrame)) {
                total += expense.getDouble("amount")
            }
        }

        return total
    }

    private fun isExpenseInTimeFrame(expenseDate: String, timeFrame: String): Boolean {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = sdf.format(Date())

        when (timeFrame) {
            "daily" -> {
                return expenseDate == currentDate
            }
            "weekly" -> {
                val currentCalendar = Calendar.getInstance()
                currentCalendar.firstDayOfWeek = Calendar.MONDAY
                val currentWeek = currentCalendar[Calendar.WEEK_OF_YEAR]

                val expenseCalendar = Calendar.getInstance()
                expenseCalendar.firstDayOfWeek = Calendar.MONDAY
                expenseCalendar.time = sdf.parse(expenseDate)

                return expenseCalendar[Calendar.WEEK_OF_YEAR] == currentWeek
            }

            "monthly" -> {
                val calendar = Calendar.getInstance()
                val currentMonth = calendar[Calendar.MONTH]
                val expenseMonth = Calendar.getInstance()
                expenseMonth.time = sdf.parse(expenseDate)
                return expenseMonth[Calendar.MONTH] == currentMonth
            }
        }

        return false
    }
}
