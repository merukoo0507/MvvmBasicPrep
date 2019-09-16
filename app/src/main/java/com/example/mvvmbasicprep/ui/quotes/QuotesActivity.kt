package com.example.mvvmbasicprep.ui.quotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmbasicprep.R
import com.example.mvvmbasicprep.data.ViewModelFactory
import com.example.mvvmbasicprep.data.model.Quote
import kotlinx.android.synthetic.main.activity_quotes.*
import timber.log.Timber

class QuotesActivity : AppCompatActivity() {

    private lateinit var viewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(this)).get(QuoteViewModel::class.java)
        viewModel.loadQuotesData()
        viewModel.quotes.observe(this, quoteObserver)

        button_add_quote.setOnClickListener {
            if (!editText_author.text.isEmpty() && !editText_quote.text.isEmpty()) {
                Timber.d("success addQuote")
                val quote = Quote(null, editText_author.text.toString(), editText_quote.text.toString())
                viewModel.addQuote(quote)
                viewModel.loadQuotesData()
            }
        }
    }

    private val quoteObserver = Observer<List<Quote>> {
        var context: String = ""
        it.forEach{
            quote -> context += "${quote.id} - ${quote.author} - ${quote.content}\n"
        }
        textView_quotes.text = context
        editText_author.text.clear()
        editText_quote.text.clear()
    }
}
