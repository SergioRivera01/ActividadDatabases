package com.sergiorivera.actividaddatabases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sergiorivera.actividaddatabases.databinding.ActivityMainBinding
import com.sergiorivera.actividaddatabases.databinding.ItemEmployeeBinding
import com.sergiorivera.actividaddatabases.db.AppDataBase
import com.sergiorivera.actividaddatabases.db.Employee
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db : AppDataBase
    private val adapter = EmployeeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvEmployee.layoutManager = LinearLayoutManager(this)
        binding.rvEmployee.adapter = adapter

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "Empleados-Database"
        )
            .allowMainThreadQueries()
            .build()

        val din = Date().time
        db.employeeDao().addEmployee(Employee("empleado #$din", "apellido #$din", 34, "I+D"))
        val employees = db.employeeDao().findAllEmployee()
        Log.d("Empleados","Empleados: $employees")

        adapter.submitList(employees)

        binding.btnAdd.setOnClickListener{
            addUser()
        }
    }


    private fun addUser() {
        val employeeName = binding.etName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val newEmployee = Employee(employeeName, lastName, 67, "C/falsa 123")
        db.employeeDao().addEmployee(newEmployee)

        refreshUsers()
    }

    private fun refreshUsers(){
        val users = db.employeeDao().findAllEmployee()
        adapter.submitList(users)
    }
}