package com.sergiorivera.actividaddatabases

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sergiorivera.actividaddatabases.databinding.ActivityMainBinding
import com.sergiorivera.actividaddatabases.db.AppDataBase
import com.sergiorivera.actividaddatabases.db.Employee
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db : AppDataBase
    private val adapter = EmployeeAdapter({
        Toast.makeText(this,"employee: $it", Toast.LENGTH_SHORT).show()
    },{
        db.employeeDao().deleteEmployee(it)
        refreshEmployee()
    }
    )
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

        /*val din = Date().time
        db.employeeDao().addEmployee(Employee("empleado #$din", "apellido #$din", 34, "I+D"))
        Log.d("Empleados","Empleados: $employees")*/

        val employees = db.employeeDao().findAllEmployee()
        adapter.submitList(employees)

        binding.btnAdd.setOnClickListener{
            addEmployee()
        }

        binding.btnIrDepartments.setOnClickListener {
            startActivity(Intent(this, DepartmentActivity::class.java))
        }

    }


    private fun addEmployee() {
        val employeeName = binding.etName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text.toString()
        val department = binding.etDepartment.text.toString()
        val newEmployee = Employee(employeeName, lastName, age, department)
        db.employeeDao().addEmployee(newEmployee)

        refreshEmployee()
    }

    private fun refreshEmployee(){
        val employees = db.employeeDao().findAllEmployee()
        adapter.submitList(employees)
    }


}