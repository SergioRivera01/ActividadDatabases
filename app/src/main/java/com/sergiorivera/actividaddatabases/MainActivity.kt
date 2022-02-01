package com.sergiorivera.actividaddatabases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.sergiorivera.actividaddatabases.db.AppDataBase
import com.sergiorivera.actividaddatabases.db.Employee

class MainActivity : AppCompatActivity() {
    private lateinit var db : AppDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "Empleados-Database"
        )
            .allowMainThreadQueries()
            .build()

        db.employeeDao().addEmployee(Employee("empleado#1", "apellido#1", 34, "I+D"))
        val employees = db.employeeDao().findAllEmployee()
        Log.d("Empleados","Empleados: $employees")
    }
}