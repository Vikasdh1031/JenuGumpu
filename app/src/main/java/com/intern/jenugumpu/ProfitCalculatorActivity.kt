package com.intern.jenugumpu

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ProfitCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profit_calculator)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "🧮 ಲಾಭ ಲೆಕ್ಕಾಚಾರ"

        val etQuantity     = findViewById<EditText>(R.id.etProfitQuantity)
        val etSellingPrice = findViewById<EditText>(R.id.etSellingPrice)
        val etFilterCost   = findViewById<EditText>(R.id.etFilteringCost)
        val btnCalculate   = findViewById<Button>(R.id.btnCalculate)
        val cardResult     = findViewById<CardView>(R.id.cardResult)
        val tvProfit       = findViewById<TextView>(R.id.tvProfit)
        val tvBreakdown    = findViewById<TextView>(R.id.tvProfitBreakdown)

        btnCalculate.setOnClickListener {
            val quantity     = etQuantity.text.toString().toDoubleOrNull()
            val sellingPrice = etSellingPrice.text.toString().toDoubleOrNull()
            val filterCost   = etFilterCost.text.toString().toDoubleOrNull()

            if (quantity == null || sellingPrice == null || filterCost == null) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val revenue      = quantity * sellingPrice
            val totalCost    = quantity * filterCost
            val profit       = revenue - totalCost
            val wholesaleRev = quantity * 150
            val extraEarned  = revenue - wholesaleRev

            tvProfit.text = "₹%.0f".format(profit)
            tvBreakdown.text =
                "Revenue: ₹%.0f\n".format(revenue) +
                        "Filtering Cost: ₹%.0f\n".format(totalCost) +
                        "vs Middleman: You earned ₹%.0f extra! 🎉".format(extraEarned)

            cardResult.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}