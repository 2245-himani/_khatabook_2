package com.kevin.incomeexpence.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.incomeexpence.DBHelper
import com.kevin.incomeexpence.Model.TransactionModel
import com.kevin.incomeexpence.TransAdapter
import com.kevin.incomeexpence.databinding.FragmentHomeBinding
import com.kevin.incomeexpence.databinding.UpdatedialogBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var dbHelper: DBHelper
    var isExpence = 0
    lateinit var adapter: TransAdapter
    var translist = ArrayList<TransactionModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        dbHelper = DBHelper(context)
        translist = dbHelper.getTransaction()
        updateTotal(translist)
        translist = translist.reversed() as ArrayList<TransactionModel>

        adapter = TransAdapter({
            updatedialog(it)
        }, {
            deleteTrans(it)
        })
        adapter.setTrans(translist)

        binding.rcvtransaction.layoutManager = LinearLayoutManager(context)
        binding.rcvtransaction.adapter = adapter

        return binding.root
    }

    private fun deleteTrans(it: Int) {

        var dialog = AlertDialog.Builder(requireContext())
            .setTitle("Delet Transaction!!")
            .setMessage("Are You Sure?")
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {

                    dbHelper.deleteTrans(it)
                    updateTotal(dbHelper.getTransaction())
                    adapter.updateData(
                        dbHelper.getTransaction().reversed() as ArrayList<TransactionModel>
                    )

                }
            }).setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {

                }
            }).create()
        dialog.show()

    }

    fun updateTotal(translist: ArrayList<TransactionModel>) {
        var totalincome = 0
        var totalExpence = 0
        for (trans in translist) {
            if (trans.isExpence == 0) {
                totalincome += trans.amount
            } else {
                totalExpence += trans.amount
            }
        }

        binding.totalincome.text = totalincome.toString()
        binding.totalexpence.text = totalExpence.toString()
        binding.totalamount.text = (totalincome - totalExpence).toString()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun updatedialog(transactionModel: TransactionModel) {
        var dialog = Dialog(requireContext())
        var bind = UpdatedialogBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.edtamount.setText(transactionModel.amount.toString())
        bind.edtcategory.setText(transactionModel.category)
        bind.edtnotes.setText(transactionModel.note)

        bind.income.setOnClickListener {
            isExpence = 0
            bind.income.setCardBackgroundColor(Color.parseColor("#22c391"))
            bind.expence.setCardBackgroundColor(Color.parseColor("#ffffff"))
            bind.txtincome.setTextColor(Color.parseColor("#ffffff"))
            bind.txtexpence.setTextColor(Color.parseColor("#707070"))
        }
        bind.expence.setOnClickListener {
            isExpence = 1
            bind.income.setCardBackgroundColor(Color.parseColor("#ffffff"))
            bind.expence.setCardBackgroundColor(Color.parseColor("#F86C98"))
            bind.txtexpence.setTextColor(Color.parseColor("#ffffff"))
            bind.txtincome.setTextColor(Color.parseColor("#717171"))
        }

        dbHelper = DBHelper(context)
        var list = dbHelper.getTransaction()

        var income = 0
        var expence = 0
        for (trans in list) {
            if (trans.isExpence == 0) {
                income += trans.amount
            } else {
                expence += trans.amount
            }
        }

        bind.btnsubmit.setOnClickListener {
            var amount = bind.edtamount.text.toString().toInt()
            var category = bind.edtcategory.text.toString()
            var note = bind.edtnotes.text.toString()
            var date = bind.txtadddate.text.toString()
            var month = bind.txtadddate.text.toString()
            var year = bind.txtadddate.text.toString()
            var model = TransactionModel(
                transactionModel.id,
                amount,
                category,
                note,
                transactionModel.isExpence,
                date,
                month,
                year
            )

            dbHelper.updateTrans(model)
            dialog.dismiss()
            adapter.updateData(dbHelper.getTransaction())
            updateTotal(dbHelper.getTransaction())
            adapter.updateData(dbHelper.getTransaction().reversed() as ArrayList<TransactionModel>)

        }

        dialog.show()
    }

}