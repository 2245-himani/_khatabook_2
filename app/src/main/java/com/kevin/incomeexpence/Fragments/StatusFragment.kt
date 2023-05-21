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
import com.kevin.incomeexpence.Model.TransactionModel
import com.kevin.incomeexpence.R
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

        val list : kotlin.collections.ArrayList<PieEntry> = java.util.ArrayList()
        list.add(PieEntry(30f,"Expence"))
        list.add(PieEntry(80f,"Income"))

        val pieDataSet = PieDataSet(list,"")
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextSize=15f
        pieDataSet.valueTextColor= Color.BLACK

        var pieData = PieData(pieDataSet)
        binding.piechart.data = pieData
        binding.piechart.description.text = "Pie Chart Of Transaction"
        binding.piechart.centerText = "Transaction List"
        binding.piechart.animateY(1300)

        return binding.root
    }

}

