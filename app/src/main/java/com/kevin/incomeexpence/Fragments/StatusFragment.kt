package com.kevin.incomeexpence.Fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.kevin.incomeexpence.DBHelper
import com.kevin.incomeexpence.Model.TransactionModel
import com.kevin.incomeexpence.R
import com.kevin.incomeexpence.TransAdapter
import com.kevin.incomeexpence.databinding.FragmentHomeBinding
import com.kevin.incomeexpence.databinding.FragmentStatusBinding
import java.util.*
import kotlin.collections.ArrayList

class StatusFragment : Fragment() {

    lateinit var binding: FragmentStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatusBinding.inflate(layoutInflater)


        return binding.root
    }

}

