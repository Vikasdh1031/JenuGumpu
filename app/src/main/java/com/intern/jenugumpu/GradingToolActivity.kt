package com.intern.jenugumpu

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class GradingToolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grading_tool)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "🎨 ಗ್ರೇಡಿಂಗ್ ಮಾರ್ಗದರ್ಶಿ"

        val spinnerColor = findViewById<Spinner>(R.id.spinnerColor)
        val spinnerDrip  = findViewById<Spinner>(R.id.spinnerDrip)
        val btnCheck     = findViewById<Button>(R.id.btnCheckGrade)
        val tvResult     = findViewById<TextView>(R.id.tvGradeResult)

        val colors = listOf("Light Golden Yellow", "Amber / Dark Yellow", "Dark Brown")
        val drips  = listOf("No drip in 10 seconds", "Drips slowly", "Drips fast")

        spinnerColor.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, colors)
        spinnerDrip.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, drips)

        btnCheck.setOnClickListener {
            val color = spinnerColor.selectedItemPosition
            val drip  = spinnerDrip.selectedItemPosition

            val result = when {
                color == 0 && drip == 0 ->
                    "🥇 Grade A — Premium!\nPrice: ₹800-₹1200/kg\n✅ Ready for retail!"
                color == 0 && drip == 1 ->
                    "🥇 Grade A — Premium!\nPrice: ₹800-₹1000/kg\n✅ Good quality!"
                color == 1 && drip == 1 ->
                    "🥈 Grade B — Standard\nPrice: ₹500-₹800/kg\n✅ Good for local market!"
                color == 1 && drip == 2 ->
                    "🥉 Grade C — Needs Filtering\nPrice: ₹200-₹500/kg\n⚠️ Filter before selling!"
                color == 2 ->
                    "🥉 Grade C — Needs Filtering\nPrice: ₹200-₹500/kg\n⚠️ High moisture detected!"
                else ->
                    "🥈 Grade B — Standard\nPrice: ₹500-₹800/kg"
            }
            tvResult.text = result
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}