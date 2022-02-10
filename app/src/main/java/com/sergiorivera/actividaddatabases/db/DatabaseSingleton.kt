package com.sergiorivera.actividaddatabases.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class DatabaseSingleton : RoomDatabase() {
    abstract fun EmployeeDao(): EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "Employee-Database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
