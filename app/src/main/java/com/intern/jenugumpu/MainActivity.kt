package com.intern.jenugumpu

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = HarvestDatabase.getDatabase(this)

        val cardHarvest = findViewById<CardView>(R.id.cardHarvest)
        val cardGrading = findViewById<CardView>(R.id.cardGrading)
        val cardPrice   = findViewById<CardView>(R.id.cardPrice)
        val cardBatch   = findViewById<CardView>(R.id.cardBatch)
        val cardProfit  = findViewById<CardView>(R.id.cardProfit)
        val tvStock     = findViewById<TextView>(R.id.tvTotalStock)
        val tvEntries   = findViewById<TextView>(R.id.tvTotalEntries)
        val tvBestSource = findViewById<TextView>(R.id.tvBestSource)
        val tvGreeting  = findViewById<TextView>(R.id.tvGreeting)

        // Greeting based on time
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        tvGreeting.text = when {
            hour < 12 -> "🌅 ಶುಭೋದಯ! Good Morning!"
            hour < 17 -> "☀️ ಶುಭ ಮಧ್ಯಾಹ್ನ! Good Afternoon!"
            else      -> "🌙 ಶುಭ ಸಂಜೆ! Good Evening!"
        }

        // Live stats from database
        lifecycleScope.launch {
            db.harvestDao().getTotalStock().collectLatest { total ->
                tvStock.text = "%.1f kg".format(total ?: 0.0)
            }
        }

        lifecycleScope.launch {
            db.harvestDao().getAllEntries().collectLatest { entries ->
                tvEntries.text = "${entries.size} entries"
                if (entries.isNotEmpty()) {
                    val best = entries.groupBy { it.floralSource }
                        .maxByOrNull { it.value.sumOf { e -> e.quantity } }
                    tvBestSource.text = best?.key?.take(15) ?: "N/A"
                } else {
                    tvBestSource.text = "No data yet"
                }
            }
        }

        cardHarvest.setOnClickListener {
            startActivity(Intent(this, HarvestLogActivity::class.java))
        }
        cardGrading.setOnClickListener {
            startActivity(Intent(this, GradingToolActivity::class.java))
        }
        cardPrice.setOnClickListener {
            startActivity(Intent(this, PriceMonitorActivity::class.java))
        }
        cardBatch.setOnClickListener {
            startActivity(Intent(this, BatchTrackerActivity::class.java))
        }
        cardProfit.setOnClickListener {
            startActivity(Intent(this, ProfitCalculatorActivity::class.java))
        }
    }
}