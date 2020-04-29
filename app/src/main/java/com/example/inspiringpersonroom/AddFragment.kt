package com.example.inspiringpersonroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.add_person_fragment.*


class AddFragment : Fragment(){
    companion object{
        lateinit var personViewModel: PersonViewModel
        fun newInstance(personViewModel: PersonViewModel): AddFragment{
            this.personViewModel = personViewModel
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
        addBtn.setOnClickListener{addPerson()}
        personViewModel.editParson.observe(viewLifecycleOwner, Observer {
            it?.let {
                populateViews(it)
            }
        })
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
                val person = Person(existingPerson.id, name, date, descr, img)
                personViewModel.update(person)
            } else {
                val person = Person(0, name, date, descr, img)
                personViewModel.insert(person)
            }
            clearFields()
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