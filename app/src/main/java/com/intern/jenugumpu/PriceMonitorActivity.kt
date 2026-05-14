package com.intern.jenugumpu

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PriceMonitorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_monitor)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "💰 ಬೆಲೆ ಮಾನಿಟರ್"

        val prefs = getSharedPreferences("prices", MODE_PRIVATE)

        val etWholesale = findViewById<EditText>(R.id.etWholesalePrice)
        val etRetail    = findViewById<EditText>(R.id.etRetailPrice)
        val btnUpdate   = findViewById<Button>(R.id.btnUpdatePrice)
        val tvWholesale = findViewById<TextView>(R.id.tvWholesaleDisplay)
        val tvRetail    = findViewById<TextView>(R.id.tvRetailDisplay)

        val savedWholesale = prefs.getFloat("wholesale", 150f)
        val savedRetail    = prefs.getFloat("retail", 800f)

        tvWholesale.text = "₹%.0f/kg".format(savedWholesale)
        tvRetail.text    = "₹%.0f/kg".format(savedRetail)

        btnUpdate.setOnClickListener {
            val w = etWholesale.text.toString().toFloatOrNull()
            val r = etRetail.text.toString().toFloatOrNull()

            if (w == null || r == null) {
                Toast.makeText(this, "Enter valid prices!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            prefs.edit().putFloat("wholesale", w).putFloat("retail", r).apply()
            tvWholesale.text = "₹%.0f/kg".format(w)
            tvRetail.text    = "₹%.0f/kg".format(r)
            etWholesale.setText("")
            etRetail.setText("")
            Toast.makeText(this, "✅ Prices updated!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}