package com.example.inspiringpersonroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.person_item.view.*

class PersonAdapter internal constructor(
    val personViewModel: PersonViewModel,
    context: Context
): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var persons = emptyList<Person>()

    inner class PersonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(person : Person){
        itemView.personName.text = person.name
            itemView.personDate.text = person.date
            itemView.personDescr.text = person.description
            Glide.with(itemView.context).load(person.imgUrl)
                .error(R.drawable.noimage)
                .into(itemView.personImage)

            itemView.editBtn.setOnClickListener{
                personViewModel.editParson.value = person
            }
            itemView.deleteBtn.setOnClickListener{
                personViewModel.delete(person)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = inflater.inflate(R.layout.person_item,parent,false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val current = persons[position]
        holder.bind(current)
    }

    internal fun setPersons(persons: List<Person>){
        this.persons = persons
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return persons.size
    }
}