package com.projects.trending.sporty.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.trending.sporty.Repository


/*
*ViewModelProvider.Factory is responsible to create your instance of ViewModel.
* */
class MainViewModelFactory(val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}