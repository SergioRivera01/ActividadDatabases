package com.sergiorivera.actividaddatabases.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Employee::class, Department::class], version = 1)
abstract  class AppDataBase : RoomDatabase() {
    abstract fun employeeDao() : EmployeeDao
}

