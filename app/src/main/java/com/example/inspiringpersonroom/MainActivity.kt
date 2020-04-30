package com.example.inspiringpersonroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.inspiringpersonroom.Adapters.FragmentAdapter
import com.example.inspiringpersonroom.ViewModels.PersonViewModel
import com.example.inspiringpersonroom.ViewModels.QuoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //val personDao = PersonDatabase.getInstance().personDao()

    private lateinit var personViewModel: PersonViewModel
    private lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUi()

        personViewModel.editParson.observe(this, Observer {
            it?.let {
                viewPager.currentItem = 1
            }
        })
        personViewModel.allPersons.observe(this, Observer {
            it?.let {
                viewPager.currentItem = 0
            }
        })

    }
    private fun setUpUi() {
        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)
        viewPager.adapter =
            FragmentAdapter(
                supportFragmentManager,
                personViewModel,
                quoteViewModel
            )
        tabLayout.setupWithViewPager(viewPager)


    }


}
