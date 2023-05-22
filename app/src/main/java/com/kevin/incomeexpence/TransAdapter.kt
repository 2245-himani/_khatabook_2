package com.kevin.incomeexpence

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.incomeexpence.Model.TransactionModel
import com.kevin.incomeexpence.databinding.ItemtransactionBinding

class TransAdapter : RecyclerView.Adapter<TransAdapter.TransHolder>() {

    var translist = ArrayList<TransactionModel>()
    var isExpence = 0

    class TransHolder(itemView: ItemtransactionBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransHolder {
        var binding =
            ItemtransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransHolder(binding)
    }

    override fun getItemCount(): Int {
        return translist.size
    }

    override fun onBindViewHolder(holder: TransHolder, position: Int) {
        holder.binding.apply {
            translist.get(position).apply {
                txtcategory.text = category
                txtnotes.text = note
                txtamount.text = amount.toString()

                if (isExpence == 0) {
                    txtamount.setTextColor(Color.GREEN)
                    listbg.setImageResource(R.drawable.transbg1)
                    imgarrow.setImageResource(R.drawable.up)
                } else {
                    txtamount.setTextColor(Color.RED)
                    listbg.setImageResource(R.drawable.transbg2)
                    imgarrow.setImageResource(R.drawable.down)
                }
            }
        }
    }

    fun setTrans(translist: ArrayList<TransactionModel>) {
        this.translist = translist
    }

}