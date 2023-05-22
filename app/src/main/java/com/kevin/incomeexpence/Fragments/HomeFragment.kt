package com.kevin.incomeexpence.Fragments

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


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var dbHelper: DBHelper
    lateinit var adapter: TransAdapter
    var translist = ArrayList<TransactionModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        dbHelper = DBHelper(context)
        translist = dbHelper.getTransaction()

        adapter = TransAdapter()
        adapter.setTrans(translist)

        binding.rcvtransaction.layoutManager = LinearLayoutManager(context)
        binding.rcvtransaction.adapter = adapter

        return binding.root
    }

}