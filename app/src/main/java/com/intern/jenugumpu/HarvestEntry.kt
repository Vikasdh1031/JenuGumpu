package com.intern.jenugumpu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "harvest_entries")
data class HarvestEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val location: String,
    val quantity: Double,
    val floralSource: String,
    val batchId: String
)