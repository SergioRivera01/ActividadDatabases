package com.sergiorivera.actividaddatabases

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergiorivera.actividaddatabases.databinding.ItemEmployeeBinding
import com.sergiorivera.actividaddatabases.db.Employee
import com.sergiorivera.actividaddatabases.db.EmployeeDao
import java.util.*

class EmployeeAdapter(
    private val onItemClick: (Employee) -> Unit,
    private val deleteEmployeeClick : (Employee) -> Unit,
   // private val editEmployeeClick : (String) -> Unit
) : ListAdapter<Employee, EmployeeAdapter.ViewHolder>(EmployeeDiffUtils()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = getItem(position)

        holder.binding.tvName.text = employee.name
        holder.binding.tvLastName.text = employee.lastName
        holder.binding.tvAge.text = employee.age.toString()
        holder.binding.tvDepartment.text = employee.department
        holder.binding.ivIcon.setColorFilter(getRandomColor())
        holder.binding.btnDelete.setOnClickListener {deleteEmployeeClick(employee)}
       // holder.binding.btnEdit.setOnClickListener {editEmployeeClick(String())}
    }


    private fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }



    inner class ViewHolder (val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root)

}

class EmployeeDiffUtils :DiffUtil.ItemCallback<Employee> (){
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean = oldItem.id == newItem.id

}