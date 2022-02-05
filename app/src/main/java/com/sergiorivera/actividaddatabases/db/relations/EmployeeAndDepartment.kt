package com.sergiorivera.actividaddatabases.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.sergiorivera.actividaddatabases.db.Department
import com.sergiorivera.actividaddatabases.db.Employee

data class EmployeeAndDepartment(
    @Embedded val department : Department,
    @Relation(
        parentColumn = "name",
        entityColumn = "name"
    )
    val employees : List<Employee>
)
