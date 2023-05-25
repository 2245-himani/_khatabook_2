package com.kevin.incomeexpence.Fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.incomeexpence.DBHelper
import com.kevin.incomeexpence.Model.TransactionModel
import com.kevin.incomeexpence.R
import com.kevin.incomeexpence.TransAdapter
import com.kevin.incomeexpence.databinding.FragmentHomeBinding
import com.kevin.incomeexpence.databinding.UpdatedialogBinding
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup


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

        adapter = TransAdapter{
            updatedialog(it)
        }
        adapter.setTrans(translist)

        binding.rcvtransaction.layoutManager = LinearLayoutManager(context)
        binding.rcvtransaction.adapter = adapter

        return binding.root
    }

    fun updateTotal(translist: ArrayList<TransactionModel>) {
        var totalincome = 0
        var totalExpence = 0
        for (trans in translist) {
            if (trans.isExpence == 0) {
                totalincome += trans.amount
            } else{
                totalExpence += trans.amount

            }
        }

        binding.totalincome.text = totalincome.toString()
        binding.totalexpence.text = totalExpence.toString()
        binding.totalamount.text = (totalincome-totalExpence).toString()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun updatedialog(transactionModel: TransactionModel) {
        var dialog = Dialog(requireContext())
        var bind = UpdatedialogBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.edtamount.setText(transactionModel.amount.toString())
        bind.edtcategory.setText(transactionModel.category)
        bind.edtnotes.setText(transactionModel.note)

        bind.radiogroup1.setOnCheckedChangeListener(object : SingleSelectToggleGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: SingleSelectToggleGroup?, checkedId: Int) {
                if (checkedId == R.id.income) {
                    isExpence = 0
                } else if (checkedId == R.id.expence) {
                    isExpence = 1
                }
            }
        })
        bind.btnsubmit.setOnClickListener {
            var amount = bind.edtamount.text.toString().toInt()
            var category = bind.edtcategory.text.toString()
            var note = bind.edtnotes.text.toString()
            var model = TransactionModel(transactionModel.id,amount, category, note, transactionModel.isExpence)

                dbHelper.updateTrans(model)
                dialog.dismiss()
                adapter.updateData(dbHelper.getTransaction())
        }
        dialog.show()
    }

}