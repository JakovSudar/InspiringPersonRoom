package com.example.inspiringpersonroom.Fragments

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.inspiringpersonroom.Entity.Quote
import com.example.inspiringpersonroom.R
import com.example.inspiringpersonroom.ViewModels.PersonViewModel
import com.example.inspiringpersonroom.ViewModels.QuoteViewModel
import kotlinx.android.synthetic.main.fragment_add_quotes.*


class AddQuotes : Fragment() {
    var numberOfQuotes = 1
    private fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
    var quoteViewModel = QuoteViewModel.getInstance(Application())

    companion object {
        var editPersonId: Int = -1
        fun newInstance(
            editPersonId: Int
        ): AddQuotes {
            Companion.editPersonId = editPersonId
            return AddQuotes()
        }
    }
    private fun populateViews() {
        if(editPersonId!=-1){
            val existingPersonQuote = quoteViewModel.getQuotesOfPerson(editPersonId)
            for(quote in existingPersonQuote){
                addEditText(quote.quote)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_quotes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        populateViews()
    }

    private fun setUpUi() {
        addEditText("")
        addMoreBtn.setOnClickListener { addEditText("") }
        deleteQuoteBtn.setOnClickListener { deleteEditText() }
        saveBtn.setOnClickListener { saveQuotes() }
    }

    private fun saveQuotes() {
        saveBtn.hideKeyboard()
        var quotes : MutableList<Quote> = ArrayList()
        var quotesEt = getEditTexts()
        for (et in quotesEt){
            if(et.text.isNotEmpty()){
                val quote = Quote(0, 0,et.text.toString())
                quotes.add(quote)
            }
        }
        //okini livedatu
        quoteViewModel.newPersonQuotes.value = quotes
        activity?.onBackPressed()
    }

    private fun getEditTexts(): ArrayList<EditText> {
        var  editTexts = ArrayList<EditText>()
        for(i in 0..numberOfQuotes-2){
            editTexts.add(quotesLayout.getChildAt(i) as EditText)
        }
        return editTexts
    }

    private fun addEditText(text:String) {
        if(numberOfQuotes<6){
            val editText = EditText(context)
            val p = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            editText.layoutParams = p
            editText.hint = "Quote "+numberOfQuotes+": "
            editText.id = numberOfQuotes
            editText.setText(text)
            quotesLayout.addView(editText)
            numberOfQuotes++
            saveBtn.visibility = View.VISIBLE
        }else
            Toast.makeText(context,"Maximum reached!", Toast.LENGTH_SHORT).show()
    }

    private fun deleteEditText() {
        if(numberOfQuotes>2) {
            quotesLayout.removeViewAt(numberOfQuotes-2)
            numberOfQuotes--
            if(numberOfQuotes == 1){
                saveBtn.visibility = View.GONE
            }
        }else
            Toast.makeText(context,"Minimum is 1!", Toast.LENGTH_SHORT).show()
    }


}