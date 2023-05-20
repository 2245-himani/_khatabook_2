package com.kevin.incomeexpence.Fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.kevin.incomeexpence.DBHelper
import com.kevin.incomeexpence.R
import com.kevin.incomeexpence.Model.TransactionModel
import com.kevin.incomeexpence.databinding.FragmentAddDataBinding
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup
import java.text.SimpleDateFormat
import java.util.Date

class   AddDataFragment : Fragment() {

    lateinit var binding: FragmentAddDataBinding
    var isExpence = 0
    lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDataBinding.inflate(layoutInflater)

        dbHelper = DBHelper(context)

        return binding.root

        initView()
    }

    private fun initView() {

        binding.btnsubmit.setOnClickListener {
            var amount = binding.edtamount.text.toString().toInt()
            var category = binding.edtcategory.text.toString()
            var note = binding.edtnotes.text.toString()

            var model = TransactionModel(1, amount, category, note, isExpence)
            dbHelper.addAmount(model)
            binding.edtamount.setText("")
            binding.edtcategory.setText("")
            binding.edtnotes.setText("")
        }

        binding.txtdate.setOnClickListener {

            var date = Date()

            var format = SimpleDateFormat("dd-MM-YYYY")
            var currentDate = format.format(date)

            var dates = currentDate.split("-")
            binding.txtdate.text = currentDate

            var dialog = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

                }

            }, dates[2].toInt(), dates[1].toInt()-1, dates[0].toInt())
            dialog.show()
        }

        binding.radiogroup.setOnCheckedChangeListener(object : MultiSelectToggleGroup.OnCheckedStateChangeListener,
            SingleSelectToggleGroup.OnCheckedChangeListener {
            override fun onCheckedStateChanged(
                group: MultiSelectToggleGroup?,
                checkedId: Int,
                isChecked: Boolean
            ) {
                if (checkedId == R.id.income) {
                    isExpence = 0
                } else if (checkedId == R.id.expence) {
                    isExpence = 1
                }
            }

            override fun onCheckedChanged(group: SingleSelectToggleGroup?, checkedId: Int) {
                if (checkedId == R.id.income) {
                    isExpence = 0
                } else if (checkedId == R.id.expence) {
                    isExpence = 1
                }
            }
        })

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

    }

}