package com.example.inspiringpersonroom.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.inspiringpersonroom.Fragments.AddFragment
import com.example.inspiringpersonroom.Fragments.RecyclerFragment
import com.example.inspiringpersonroom.ViewModels.PersonViewModel
import com.example.inspiringpersonroom.ViewModels.QuoteViewModel

class FragmentAdapter(
    fragmentManager: FragmentManager

): FragmentPagerAdapter(fragmentManager) {
    private val fragments = arrayOf(
        RecyclerFragment.newInstance(),
        AddFragment.newInstance()
    )
    private val titles = arrayOf("Persons", "Add")

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
    override fun getCount(): Int {
        return fragments.size
    }
}