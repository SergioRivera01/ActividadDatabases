package com.sergiorivera.actividaddatabases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sergiorivera.actividaddatabases.databinding.ActivityDepartmentBinding
import com.sergiorivera.actividaddatabases.databinding.ActivityMainBinding
import com.sergiorivera.actividaddatabases.db.AppDataBase
import com.sergiorivera.actividaddatabases.db.Department
import com.sergiorivera.actividaddatabases.db.Employee

class DepartmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDepartmentBinding
    private lateinit var db : AppDataBase
    private val adapter = DepartmentAdapter({
        Toast.makeText(this,"employee: $it", Toast.LENGTH_SHORT).show()
    },{
        db.employeeDao().deleteDepartment(it)
        refreshDepartment()
    }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepartmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDepartments.layoutManager = LinearLayoutManager(this)
        binding.rvDepartments.adapter = adapter

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "Empleados-Database"
        )
            .allowMainThreadQueries()
            .build()

        refreshDepartment()

        binding.btnAdd.setOnClickListener{
           // addDepartment()
            getRelation()
        }

        binding.btnAtras.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun getRelation(){
        val nameDepartment = binding.etNameDepartment.text.toString()
        db.employeeDao().getDepartmentWithEmployee(nameDepartment)
        refreshDepartment()
    }

    private fun addDepartment() {
        val departmentName = binding.etNameDepartment.text.toString()
        val departmentDescription = binding.etDescription.text.toString()
        val department = Department(departmentName,departmentDescription )

        db.employeeDao().addDepartment(department)

        refreshDepartment()
    }

    private fun refreshDepartment(){
        val departments =db.employeeDao().findAllDepertment()
        adapter.submitList(departments)
    }
}