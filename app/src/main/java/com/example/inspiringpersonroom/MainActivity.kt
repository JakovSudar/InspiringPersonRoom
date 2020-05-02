package com.example.inspiringpersonroom

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.inspiringpersonroom.Adapters.FragmentAdapter
import com.example.inspiringpersonroom.ViewModels.PersonViewModel
import com.example.inspiringpersonroom.ViewModels.QuoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var personViewModel: PersonViewModel
    private lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
        setUpViewModels()
        setUpObservers()
    }
    private fun setUpUi() {
        viewPager.adapter =
            FragmentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
    private fun setUpViewModels() {
        quoteViewModel = QuoteViewModel.getInstance(Application())
        personViewModel = PersonViewModel.getInstance(Application())
    }
    private fun setUpObservers() {
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
}
