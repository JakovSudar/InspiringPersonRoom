package com.example.inspiringpersonroom.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.inspiringpersonroom.Entity.Quote
import com.example.inspiringpersonroom.R
import com.example.inspiringpersonroom.ViewModels.QuoteViewModel
import kotlinx.android.synthetic.main.fragment_add_quotes.*

class AddQuotes : Fragment() {
    var numberOfQuotes = 1

    companion object {
        lateinit var quoteViewModel: QuoteViewModel
        fun newInstance(quoteViewModel: QuoteViewModel):AddQuotes{
            Companion.quoteViewModel = quoteViewModel
            return AddQuotes()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMoreBtn.setOnClickListener{addEditText()}
        deleteQuoteBtn.setOnClickListener{deleteEditText()}
        saveBtn.setOnClickListener{saveQuotes()}
    }

    private fun saveQuotes() {
        var quotes : MutableList<Quote> = ArrayList()
        var quotesEt = getEditTexts()
        for (et in quotesEt){
            val quote = Quote(0, 0,et.text.toString())
            quotes.add(quote)
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

    private fun addEditText() {
        if(numberOfQuotes<6){
            val editText = EditText(context)
            val p = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            editText.layoutParams = p
            editText.hint = "Quote "+numberOfQuotes+": "
            editText.id = numberOfQuotes
            quotesLayout.addView(editText)
            numberOfQuotes++
            saveBtn.visibility = View.VISIBLE
        }else
            Toast.makeText(context,"Maximum reached!", Toast.LENGTH_SHORT).show()
    }

    private fun deleteEditText() {
        if(numberOfQuotes>1) {
            quotesLayout.removeViewAt(numberOfQuotes-2)
            numberOfQuotes--
            if(numberOfQuotes == 1){
                saveBtn.visibility = View.GONE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_quotes, container, false)
    }
}