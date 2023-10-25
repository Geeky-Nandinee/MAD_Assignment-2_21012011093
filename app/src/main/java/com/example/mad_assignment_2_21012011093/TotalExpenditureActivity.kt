package com.example.mad_assignment_2_21012011093

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TotalExpenditureActivity : AppCompatActivity() {
    private lateinit var radioGroupTimeFrame: RadioGroup
    private lateinit var radioButtonDaily: RadioButton
    private lateinit var radioButtonWeekly: RadioButton
    private lateinit var radioButtonMonthly: RadioButton
    private lateinit var textViewTotalExpenditure: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_expenditure)

        // Initialize UI elements
        radioGroupTimeFrame = findViewById(R.id.radioGroupTimeFrame)
        radioButtonDaily = findViewById(R.id.radioButtonDaily)
        radioButtonWeekly = findViewById(R.id.radioButtonWeekly)
        radioButtonMonthly = findViewById(R.id.radioButtonMonthly)
        textViewTotalExpenditure = findViewById(R.id.textViewTotalExpenditure)

        // Set up radio button listeners
        radioGroupTimeFrame.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButtonDaily -> {
                    // Calculate and display total daily expenditure
                    val totalDailyExpenditure = calculateTotalExpenditureFor("daily")
                    textViewTotalExpenditure.text = "Total Expenditure (Daily): $totalDailyExpenditure"
                }
                R.id.radioButtonWeekly -> {
                    // Calculate and display total weekly expenditure
                    val totalWeeklyExpenditure = calculateTotalExpenditureFor("weekly")
                    textViewTotalExpenditure.text = "Total Expenditure (Weekly): $totalWeeklyExpenditure"
                }
                R.id.radioButtonMonthly -> {
                    // Calculate and display total monthly expenditure
                    val totalMonthlyExpenditure = calculateTotalExpenditureFor("monthly")
                    textViewTotalExpenditure.text = "Total Expenditure (Monthly): $totalMonthlyExpenditure"
                }
            }
        }
    }

    private fun calculateTotalExpenditureFor(timeFrame: String): Double {
        // Placeholder for calculating the total expenditure based on the selected time frame
        // You should replace this with your logic to calculate the actual total expenditure
        // Sample: Calculate a random total expenditure
        return when (timeFrame) {
            "daily" -> 75.0
            "weekly" -> 450.0
            "monthly" -> 1900.0
            else -> 0.0
        }
    }
}