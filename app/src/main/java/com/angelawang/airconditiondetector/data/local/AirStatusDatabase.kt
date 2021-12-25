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
            ).addCallback(databaseCallback).build()
        }

        private var databaseCallback = object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch {
                    INSTANCE?.airStatusDao()?.insert(AirStatus("1", "site_name_1", "county_1", "pm25_1", "status_1"))
                    INSTANCE?.airStatusDao()?.insert(AirStatus("2", "site_name_2", "county_2", "pm25_2", "status_2"))
                    INSTANCE?.airStatusDao()?.insert(AirStatus("3", "site_name_3", "county_3", "pm25_3", "status_3"))
                }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}