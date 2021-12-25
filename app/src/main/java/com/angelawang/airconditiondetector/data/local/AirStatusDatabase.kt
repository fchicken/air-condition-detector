package com.angelawang.airconditiondetector.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.angelawang.airconditiondetector.data.model.AirStatus
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [AirStatus::class], version = 1)
abstract class AirStatusDatabase: RoomDatabase() {

    abstract fun airStatusDao(): AirStatusDao

    companion object {

        @Volatile
        private var INSTANCE: AirStatusDatabase? = null

        fun getInstance(context: Context): AirStatusDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).apply { INSTANCE = this }
            }
        }

        private fun buildDatabase(context: Context): AirStatusDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AirStatusDatabase::class.java,
                AirStatusDatabase::class.java.simpleName
            ).build()
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}