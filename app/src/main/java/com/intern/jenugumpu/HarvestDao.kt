package com.intern.jenugumpu

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HarvestDao {

    @Insert
    suspend fun insertEntry(entry: HarvestEntry)

    @Update
    suspend fun updateEntry(entry: HarvestEntry)

    @Delete
    suspend fun deleteEntry(entry: HarvestEntry)

    @Query("SELECT * FROM harvest_entries ORDER BY id DESC")
    fun getAllEntries(): Flow<List<HarvestEntry>>

    @Query("SELECT SUM(quantity) FROM harvest_entries")
    fun getTotalStock(): Flow<Double?>

    @Query("SELECT * FROM harvest_entries WHERE id = :id")
    suspend fun getEntryById(id: Int): HarvestEntry?
}