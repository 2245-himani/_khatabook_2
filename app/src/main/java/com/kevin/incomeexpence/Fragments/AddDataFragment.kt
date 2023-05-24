package com.kevin.incomeexpence.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.kevin.incomeexpence.DBHelper
import com.kevin.incomeexpence.R
import com.kevin.incomeexpence.Model.TransactionModel
import com.kevin.incomeexpence.databinding.FragmentAddDataBinding
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Date

class AddDataFragment : Fragment() {

    lateinit var binding: FragmentAddDataBinding
    var isExpence = 0
    lateinit var dbHelper: DBHelper
    lateinit var trans: TransactionModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDataBinding.inflate(layoutInflater)

        initView()

        dbHelper = DBHelper(context)

        return binding.root
    }

    private fun initView() {

        binding.txtdate.setOnClickListener {

            var date = Date()

            var format1 = SimpleDateFormat("dd-MM-YYYY")
            var currentDate = format1.format(date)

            var dates = currentDate.split("-")
            binding.txtdate.text = currentDate

            var dialog =
                DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

                        var seleYear = p1
                        var seleMonth = p2+1
                        var seleDate = p3

                        var selectedDate = "$p3-${(p2 + 1)}-$p1"
                        binding.txtdate.text = selectedDate
                    }

                }, dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
            dialog.show()
        }

        binding.txttime.setOnClickListener {

            var date = Date()

            var format2 = SimpleDateFormat("hh:mm a")
            var currentTime = format2.format(date)

            binding.txttime.text = currentTime
            var seleTime = currentTime

            var dialog1 = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

                }

            }, 10, 0, false)

            dialog1.show()

        }

        binding.income.setOnClickListener {
            isExpence = 0
            binding.income.setCardBackgroundColor(Color.parseColor("#93FAA4"))
            binding.expence.setCardBackgroundColor(Color.parseColor("#ffffff"))
            binding.txtincome.setTextColor(Color.parseColor("#ffffff"))
            binding.txtexpence.setTextColor(Color.parseColor("#707070"))
        }
        binding.expence.setOnClickListener {
            isExpence = 1
            binding.income.setCardBackgroundColor(Color.parseColor("#ffffff"))
            binding.expence.setCardBackgroundColor(Color.parseColor("#F86C98"))
            binding.txtexpence.setTextColor(Color.parseColor("#ffffff"))
            binding.txtincome.setTextColor(Color.parseColor("#717171"))
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

        binding.btnsubmit.setOnClickListener {
            var amount = binding.edtamount.text.toString().toInt()
            var category = binding.edtcategory.text.toString()
            var note = binding.edtnotes.text.toString()

            if (category.isEmpty() || note.isEmpty() || amount==0){
                Toast.makeText(context, "Please enter data", Toast.LENGTH_SHORT).show()
            } else {
                var model = TransactionModel(1, amount, category, note, isExpence)
                dbHelper.addAmount(model)
                binding.edtamount.setText("")
                binding.edtcategory.setText("")
                binding.edtnotes.setText("")
            }
        }
    }

}