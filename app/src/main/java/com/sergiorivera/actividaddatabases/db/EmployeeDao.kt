package com.sergiorivera.actividaddatabases.db

import androidx.room.*

@Dao
interface EmployeeDao {

    //Buscar todos los empleados
    @Query("select * from employee")
    fun findAllEmployee() : List<Employee>

    //Buscar por empleado por id
    @Query("select * from employee where Employee.Id = :employeeId")
    fun findByIdEmployee(employeeId : Int) : List<Employee>

    //Insertar empleado
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEmployee(employee: Employee)

    //Eliminar usuario
    @Delete
    fun deleteEmployee(employee: Employee)
}

//Buscar todos los departamentos
//@Query("select * from department")
//fun findAllDepertment() : List<Department>

//Buscar departameto por id
// @Query("select * from department where Department.id = :departmentId limit 1")
//fun findByIdDepartment(departmentId : Int) : List<Department>