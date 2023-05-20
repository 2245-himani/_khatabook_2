package com.kevin.incomeexpence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.incomeexpence.Model.TransactionModel

class TransAdapter(list: ArrayList<TransactionModel>): RecyclerView.Adapter<TransAdapter.TransHolder>() {

    var list = list
    class TransHolder (itemView: View): ViewHolder(itemView){
        var amount = itemView.findViewById<TextView>(R.id.amount)
        var category = itemView.findViewById<TextView>(R.id.category)
        var notes = itemView.findViewById<TextView>(R.id.notes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.itemtransaction,parent,false)
        return TransHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TransHolder, position: Int) {
        holder.amount.text = list.get(position).amount.toString()
        holder.category.text = list.get(position).category
        holder.notes.text = list.get(position).note
    }

    fun update(transaction: java.util.ArrayList<TransactionModel>) {
        list = transaction
        notifyDataSetChanged()
    }
}