package com.example.mvvmbasicprep.ui.quotes

import android.app.Activity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvmbasicprep.data.DataRepository
import com.example.mvvmbasicprep.data.model.Quote
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class QuoteViewModel(private val activity: Activity,
                     private val repo: DataRepository) : AndroidViewModel(activity.application) {

    var quotes: MutableLiveData<List<Quote>> = MutableLiveData()

//    fun getQuotes(): List<Quote>? {
//        return quotes.value
//    }

    fun loadQuotesData() {
        Timber.d("loadQuotesData")
        repo.getAllQuotes(true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("success getQnotesData")
                quotes.value = it
            }, {
                Timber.i("Throwable $it")
            })
    }

    fun addQuote(item: Quote) {
        repo.addQuote(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("success addQuote")
            }, {
                Timber.i( "Throwble $it")
            })
    }
}