package com.fsi.swipeexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import kotlinx.android.synthetic.main.item_employee_swipe.view.*
import java.lang.String
import java.util.*

class SwipeAdapter(
    private val context: Context,
    private var employees: ArrayList<Employee>
) :
    RecyclerView.Adapter<SwipeAdapter.SwipeViewHolder?>() {
    private val viewBinderHelper = ViewBinderHelper()
    fun setEmployees(employees: ArrayList<Employee>) {
        this.employees = ArrayList()
        this.employees = employees
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_employee_swipe, parent, false)
        return SwipeViewHolder(view)
    }

    override fun onBindViewHolder (
        @NonNull swipeViewHolder: SwipeViewHolder,
        i: Int
    ) {
        viewBinderHelper.setOpenOnlyOne(true)
        viewBinderHelper.bind(
            swipeViewHolder.itemView.swipelayout,
            String.valueOf(employees[i].name)
        )
        viewBinderHelper.closeLayout(String.valueOf(employees[i].name))
        swipeViewHolder.bindData(employees[i])
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    inner class SwipeViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bindData(employee: Employee) {
            itemView.textView.text = employee.name
        }
        init {
            itemView.txtEdit.setOnClickListener {
                Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show()
            }
            itemView.txtDelete.setOnClickListener {
                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
            }
        }
    }

}