package com.sergiorivera.actividaddatabases

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergiorivera.actividaddatabases.databinding.ItemDepartmentBinding
import com.sergiorivera.actividaddatabases.db.Department
import java.util.*

class DepartmentAdapter (
    private val onItemClick : (Department) -> Unit,
    private val deleteEmployeeClick : (Department) -> Unit
        ) : ListAdapter<Department, DepartmentAdapter.ViewHolder>(DepartmentDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDepartmentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val department = getItem(position)

        holder.binding.tvName.text = department.name
        holder.binding.tvDescription.text = department.description
        holder.binding.btnDelete.setOnClickListener { deleteEmployeeClick(department) }
        holder.binding.ivIcon.setColorFilter(getRandomColor())
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    inner class ViewHolder (val binding: ItemDepartmentBinding) : RecyclerView.ViewHolder(binding.root)


}

class DepartmentDiffUtils : DiffUtil.ItemCallback<Department> () {
    override fun areItemsTheSame(oldItem: Department, newItem: Department): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Department, newItem: Department): Boolean = oldItem.id == newItem.id
}