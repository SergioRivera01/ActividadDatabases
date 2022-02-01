package com.sergiorivera.actividaddatabases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergiorivera.actividaddatabases.databinding.ItemEmployeeBinding
import com.sergiorivera.actividaddatabases.db.Employee

class EmployeeAdapter : ListAdapter<Employee, EmployeeAdapter.ViewHolder>(EmployeeDiffUtils()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = getItem(position)

        holder.binding.tvName.text = employee.name
        holder.binding.tvLastName.text = employee.lastName
    }

    inner class ViewHolder (val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root)

}

class EmployeeDiffUtils :DiffUtil.ItemCallback<Employee> (){
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean = oldItem.id == newItem.id

}