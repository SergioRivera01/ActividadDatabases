package com.sergiorivera.actividaddatabases.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "lastName")
    val lastName : String,
    @ColumnInfo(name = "age")
    val age : Int,
    @ColumnInfo(name = "department")
    val department : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
)