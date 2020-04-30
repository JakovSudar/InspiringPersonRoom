package com.example.inspiringpersonroom.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.inspiringpersonroom.Entity.Person
import com.example.inspiringpersonroom.Entity.Quote
import com.example.inspiringpersonroom.R
import com.example.inspiringpersonroom.ViewModels.PersonViewModel
import com.example.inspiringpersonroom.ViewModels.QuoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_person_fragment.*


class AddFragment : Fragment(){
    var newPersonQuotes : List<Quote> =ArrayList()
    var editingPersonId: Int = -1

    companion object{
        lateinit var personViewModel: PersonViewModel
        lateinit var quoteViewModel: QuoteViewModel
        fun newInstance(personViewModel: PersonViewModel,quoteViewModel: QuoteViewModel): AddFragment {
            Companion.personViewModel = personViewModel
            Companion.quoteViewModel = quoteViewModel
            return AddFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_person_fragment, container,false)
    }

    private fun populateViews(person: Person) {
        newName.setText(person.name)
        newDescription.setText(person.description)
        newDate.setText(person.date)
        newImage.setText(person.imgUrl)
        personId.text = (person.id.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpListeners()
        setUpObservers()
    }

    private fun setUpObservers() {
        personViewModel.editParson.observe(viewLifecycleOwner, Observer {
            it?.let {
                populateViews(it)
                editingPersonId = it.id
            }
        })
        quoteViewModel.newPersonQuotes.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.newPersonQuotes = it
            }
        })
    }

    private fun setUpListeners() {
        addBtn.setOnClickListener{addPerson()}
        quotesBtn.setOnClickListener{openQuotesFragment()}
        cancelBtn.setOnClickListener{cancelAdding()}
    }

    private fun cancelAdding() {
        clearFields()
        editingPersonId = -1
    }

    private fun openQuotesFragment() {
        val addQuotes = AddQuotes.newInstance(personViewModel, quoteViewModel,editingPersonId)
        activity?.supportFragmentManager?.beginTransaction()
            ?.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.enter,R.anim.exit)
            ?.addToBackStack(null)
            ?.add(R.id.screenLayout,addQuotes)?.commit()
    }

    private fun addPerson() {
        val id = personId.text
        val existingPerson = personViewModel.find(Integer.parseInt(id.toString()))
        val name = newName.text.toString()
        val date = newDate.text.toString()
        val descr = newDescription.text.toString()
        val img = newImage.text.toString()
        if(name.isNotBlank() && date.isNotBlank() && descr.isNotBlank()) {
            if (existingPerson != null) {
                val person = Person(
                    existingPerson.id,
                    name,
                    date,
                    descr,
                    img
                )
                //bilo je promjena u quotovima
                if(newPersonQuotes.isNotEmpty()){
                    quoteViewModel.deletePersonQuote(person.id)
                    quoteViewModel.insertMultiple(person,newPersonQuotes)
                }
                personViewModel.update(person)
                clearFields()
                editingPersonId = -1
                newPersonQuotes = emptyList()
            } else if(newPersonQuotes.isNotEmpty()){
                val person = Person(
                    0,
                    name,
                    date,
                    descr,
                    img
                )
                personViewModel.insert(person)
                var nperson = personViewModel.findByName(person.name)
                quoteViewModel.insertMultiple(nperson,newPersonQuotes)
                newPersonQuotes = emptyList()
                clearFields()
                editingPersonId = -1
            }else Toast.makeText(context,"Add Quotes!", Toast.LENGTH_SHORT).show()

        } else Toast.makeText(context,"Empty field!", Toast.LENGTH_SHORT).show()

    }
    private fun clearFields(){
        personId.text="-1"
        newDate.text.clear()
        newDescription.text.clear()
        newName.text.clear()
        newImage.text.clear()
    }
}