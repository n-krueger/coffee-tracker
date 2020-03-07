package com.example.coffeetracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CoffeeEvent::class], version = 1)
@TypeConverters(CoffeeTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coffeeEventDao(): CoffeeEventDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "coffeeDb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
