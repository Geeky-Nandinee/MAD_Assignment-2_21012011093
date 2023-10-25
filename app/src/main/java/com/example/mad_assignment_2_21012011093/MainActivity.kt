package com.example.mad_assignment_2_21012011093

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTotalExpenditure = findViewById<Button>(R.id.btnTotalExpenditure)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)

        btnTotalExpenditure.setOnClickListener {
            // Navigate to TotalExpenditureActivity
            val intent = Intent(this, TotalExpenditureActivity::class.java)
            startActivity(intent)
        }

        btnAddExpense.setOnClickListener {
            // Navigate to AddExpenseActivity
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }
    }
}