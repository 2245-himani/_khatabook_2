package com.kevin.incomeexpence.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevin.incomeexpence.R
import com.kevin.incomeexpence.TransactionModel
import com.kevin.incomeexpence.databinding.FragmentAddDataBinding


class   AddDataFragment : Fragment() {

    lateinit var binding: FragmentAddDataBinding
    var isExpence = 0
    lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDataBinding.inflate(layoutInflater)

        binding.btnsubmit.setOnClickListener {

            var amt = binding.edtamount.text.toString().toInt()
            var category = binding.edtcategory.text.toString()
            var notes = binding.edtnotes.text.toString()

            var model = TransactionModel(0, amt, category, notes, isExpence)
            dbHelper.addAmount(model)
        }
        return binding.root
    }
}