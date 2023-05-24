package com.kevin.incomeexpence

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.incomeexpence.Model.TransactionModel
import com.kevin.incomeexpence.databinding.ItemtransactionBinding

class TransAdapter(update: (TransactionModel) -> Unit, delete:(Int) -> Unit) : RecyclerView.Adapter<TransAdapter.TransHolder>() {

    var update = update
    var delete = delete

    var translist = ArrayList<TransactionModel>()
    lateinit var context: Context

    class TransHolder(itemView: ItemtransactionBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransHolder {
        context = parent.context
        var binding = ItemtransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransHolder(binding)
    }

    override fun getItemCount(): Int {
        return translist.size
    }

    override fun onBindViewHolder(holder: TransHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.apply {
            translist.get(position).apply {
                txtcategory.text = category
                txtnotes.text = note
                txtamount.text = amount.toString()

                if (isExpence == 0) {
                    txtamount.setTextColor(Color.parseColor("#05C005"))
                    listbg.setImageResource(R.drawable.transbg1)
                    imgarrow.setImageResource(R.drawable.up)
                    cardbg.setCardBackgroundColor(Color.parseColor("#F0FDF4"))
                } else {
                    txtamount.setTextColor(Color.RED)
                    listbg.setImageResource(R.drawable.transbg2)
                    imgarrow.setImageResource(R.drawable.down)
                    cardbg.setCardBackgroundColor(Color.parseColor("#FFF9F9"))
                }
            }
        }

        holder.itemView.setOnLongClickListener(object : OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {

                var popupMenu = PopupMenu(context, holder.itemView)
                popupMenu.menuInflater.inflate(R.menu.optionmenu, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
                    override fun onMenuItemClick(p0: MenuItem?): Boolean {

                        if (p0?.itemId == R.id.edit) {
                            update.invoke(translist.get(position))
                        }

                        if (p0?.itemId == R.id.delete) {
                            delete.invoke(translist.get(position).id)
                        }
                        return true
                    }
                })
                popupMenu.show()
                return true
            }
        })
    }

    fun setTrans(translist: ArrayList<TransactionModel>) {
        this.translist = translist
    }

    fun updateData(transaction: ArrayList<TransactionModel>) {
        translist = transaction
        notifyDataSetChanged()
    }

}