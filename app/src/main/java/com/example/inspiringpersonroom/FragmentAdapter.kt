package com.example.inspiringpersonroom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(
    fragmentManager: FragmentManager,
    personViewModel: PersonViewModel
): FragmentPagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        RecyclerFragment.newInstance(personViewModel),
        AddFragment.newInstance(personViewModel)
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