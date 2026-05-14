package com.intern.jenugumpu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HarvestEntry::class], version = 1)
abstract class HarvestDatabase : RoomDatabase() {

    abstract fun harvestDao(): HarvestDao

    companion object {
        @Volatile
        private var INSTANCE: HarvestDatabase? = null

        fun getDatabase(context: Context): HarvestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HarvestDatabase::class.java,
                    "harvest_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}