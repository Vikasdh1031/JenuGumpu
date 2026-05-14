package com.intern.jenugumpu

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HarvestLogActivity : AppCompatActivity() {

    private lateinit var db: HarvestDatabase
    private lateinit var adapter: HarvestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harvest_log)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "📝 ಸಂಗ್ರಹ ದಾಖಲೆ"

        db = HarvestDatabase.getDatabase(this)

        val etDate     = findViewById<EditText>(R.id.etDate)
        val etLocation = findViewById<EditText>(R.id.etLocation)
        val etQuantity = findViewById<EditText>(R.id.etQuantity)
        val rgFloral   = findViewById<RadioGroup>(R.id.rgFloralSource)
        val btnSave    = findViewById<Button>(R.id.btnSave)
        val rvEntries  = findViewById<RecyclerView>(R.id.rvEntries)

        val today = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        etDate.setText(today)

        adapter = HarvestAdapter(emptyList(),
            onDelete = { entry ->
                AlertDialog.Builder(this)
                    .setTitle("Delete Entry?")
                    .setMessage("Delete batch ${entry.batchId}?")
                    .setPositiveButton("Delete") { _, _ ->
                        lifecycleScope.launch {
                            db.harvestDao().deleteEntry(entry)
                            runOnUiThread {
                                Toast.makeText(this@HarvestLogActivity,
                                    "✅ Entry deleted!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        )

        rvEntries.layoutManager = LinearLayoutManager(this)
        rvEntries.adapter = adapter

        lifecycleScope.launch {
            db.harvestDao().getAllEntries().collectLatest { entries ->
                adapter.updateData(entries)
            }
        }

        btnSave.setOnClickListener {
            val date       = etDate.text.toString()
            val location   = etLocation.text.toString()
            val quantity   = etQuantity.text.toString().toDoubleOrNull()
            val selectedId = rgFloral.checkedRadioButtonId

            if (location.isEmpty() || quantity == null || selectedId == -1) {
                Toast.makeText(this,
                    "ದಯವಿಟ್ಟು ಎಲ್ಲಾ ಮಾಹಿತಿ ತುಂಬಿರಿ! (Fill all fields!)",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val floralSource = findViewById<RadioButton>(selectedId).text.toString()
            val batchId = "JG-" + System.currentTimeMillis().toString().takeLast(6)

            lifecycleScope.launch {
                db.harvestDao().insertEntry(
                    HarvestEntry(
                        date = date,
                        location = location,
                        quantity = quantity,
                        floralSource = floralSource,
                        batchId = batchId
                    )
                )
                runOnUiThread {
                    etLocation.setText("")
                    etQuantity.setText("")
                    rgFloral.clearCheck()
                    Toast.makeText(this@HarvestLogActivity,
                        "✅ ದಾಖಲೆ ಉಳಿಸಲಾಗಿದೆ! Batch: $batchId",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

class HarvestAdapter(
    private var entries: List<HarvestEntry>,
    private val onDelete: (HarvestEntry) -> Unit
) : RecyclerView.Adapter<HarvestAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvEntry: TextView = view.findViewById(R.id.tvEntryText)
        val btnDelete: Button = view.findViewById(R.id.btnDeleteEntry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_harvest_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.tvEntry.text =
            "📅 ${entry.date} | 📍 ${entry.location}\n" +
                    "⚖️ ${entry.quantity} kg | 🌸 ${entry.floralSource}\n" +
                    "🏷️ Batch: ${entry.batchId}"
        holder.btnDelete.setOnClickListener { onDelete(entry) }
    }

    override fun getItemCount() = entries.size

    fun updateData(newEntries: List<HarvestEntry>) {
        entries = newEntries
        notifyDataSetChanged()
    }
}