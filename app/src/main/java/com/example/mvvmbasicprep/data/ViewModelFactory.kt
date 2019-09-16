package com.example.mvvmbasicprep.data

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmbasicprep.ui.quotes.QuoteViewModel
import com.example.mvvmbasicprep.utilities.Injection

class ViewModelFactory private constructor(
    private val activity: Activity,
    private val repo: DataRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(QuoteViewModel::class.java) ->
                    QuoteViewModel(activity, repo)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(activity: Activity) =
            INSTANCE ?: synchronized(
                ViewModelFactory::class.java) {
                INSTANCE
                    ?: ViewModelFactory(
                        activity,
                        Injection.provideRepository(activity.applicationContext)
                    )
                    .also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}