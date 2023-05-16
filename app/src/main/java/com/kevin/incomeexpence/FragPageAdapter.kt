package com.kevin.incomeexpence

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragPageAdapter(fm: FragmentManager, fragment: Array<Fragment>) : FragmentPagerAdapter(fm) {

    var fragment = fragment
    override fun getCount(): Int {
        return fragment.size
    }

    override fun getItem(position: Int): Fragment {
        return fragment.get(position)
    }

}