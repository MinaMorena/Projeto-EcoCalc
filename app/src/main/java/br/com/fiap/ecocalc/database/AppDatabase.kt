package br.com.fiap.ecocalc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.ecocalc.dao.FlightEstimateDao
import br.com.fiap.ecocalc.model.FlightEstimate

@Database(entities = [FlightEstimate::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun flightEstimateDao(): FlightEstimateDao

    companion object {
        @Volatile
        private lateinit var instance: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "flight_estimates"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}