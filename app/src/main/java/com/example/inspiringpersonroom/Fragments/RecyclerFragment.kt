package com.example.inspiringpersonroom.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inspiringpersonroom.Adapters.PersonAdapter
import com.example.inspiringpersonroom.R
import com.example.inspiringpersonroom.ViewModels.PersonViewModel
import com.example.inspiringpersonroom.ViewModels.QuoteViewModel
import kotlinx.android.synthetic.main.recycler_fragment.*

class RecyclerFragment : Fragment() {
    companion object{
        lateinit var personViewModel: PersonViewModel
        lateinit var quoteViewModel: QuoteViewModel
        fun newInstance(personViewModel: PersonViewModel, quoteViewModel: QuoteViewModel): RecyclerFragment {
            Companion.personViewModel = personViewModel
            Companion.quoteViewModel = quoteViewModel
            return RecyclerFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.recycler_fragment, container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        val adapter = PersonAdapter(
            personViewModel,
            quoteViewModel,
            context!!
        )
        recycler.adapter = adapter
        personViewModel.allPersons.observe(viewLifecycleOwner, androidx.lifecycle.Observer { persons->
            persons?.let{persons->
                persons?.let{ adapter.setPersons(it)}
            } })
    }
}