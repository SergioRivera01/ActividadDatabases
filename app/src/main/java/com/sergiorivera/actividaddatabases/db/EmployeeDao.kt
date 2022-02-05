package com.sergiorivera.actividaddatabases.db

import androidx.room.*
import com.sergiorivera.actividaddatabases.db.relations.EmployeeAndDepartment

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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDepartment(department: Department)

    //Eliminar usuario
    @Delete
    fun deleteEmployee(employee: Employee)
    @Delete
    fun deleteDepartment(department: Department)

    //Editar
  //  @Query("UPDATE Em SET name = :juegosName WHERE id = :juegosId")
   // fun update2(juegosName: String, juegosId: Int)

    //Buscar todos los departamentos
    @Query("select * from department")
    fun findAllDepertment() : List<Department>

    //Buscar departameto por id
    @Query("select * from department where Department.id = :departmentId limit 1")
    fun findByIdDepartment(departmentId : Int) : List<Department>

    //Relacion
    @Transaction
    @Query("select * from  department where name = :name")
    fun getDepartmentWithEmployee(name : String) : List<EmployeeAndDepartment>
}

