package com.intern.jenugumpu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BatchTrackerActivity : AppCompatActivity() {

    private lateinit var db: HarvestDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batch_tracker)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "📦 ಬ್ಯಾಚ್ ಟ್ರ್ಯಾಕರ್"

        db = HarvestDatabase.getDatabase(this)

        val rvBatches   = findViewById<RecyclerView>(R.id.rvBatches)
        val tvNoBatches = findViewById<TextView>(R.id.tvNoBatches)

        val adapter = BatchAdapter(emptyList())
        rvBatches.layoutManager = LinearLayoutManager(this)
        rvBatches.adapter = adapter

        lifecycleScope.launch {
            db.harvestDao().getAllEntries().collectLatest { entries ->
                if (entries.isEmpty()) {
                    tvNoBatches.visibility = View.VISIBLE
                    rvBatches.visibility   = View.GONE
                } else {
                    tvNoBatches.visibility = View.GONE
                    rvBatches.visibility   = View.VISIBLE
                    adapter.updateData(entries)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

class BatchAdapter(private var entries: List<HarvestEntry>) :
    RecyclerView.Adapter<BatchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvBatchId: TextView   = view.findViewById(R.id.tvBatchId)
        val tvDetails: TextView   = view.findViewById(R.id.tvBatchDetails)
        val tvQuantity: TextView  = view.findViewById(R.id.tvBatchQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_batch, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.tvBatchId.text  = "🏷️ ${entry.batchId}"
        holder.tvDetails.text  = "📍 ${entry.location} | 🌸 ${entry.floralSource}\n📅 ${entry.date}"
        holder.tvQuantity.text = "${entry.quantity} kg"
    }

    override fun getItemCount() = entries.size

    fun updateData(newEntries: List<HarvestEntry>) {
        entries = newEntries
        notifyDataSetChanged()
    }
}