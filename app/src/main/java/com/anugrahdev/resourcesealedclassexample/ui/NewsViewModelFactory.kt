package com.anugrahdev.resourcesealedclassexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anugrahdev.resourcesealedclassexample.network.NewsRepository

class NewsViewModelFactory(
    private val repository: NewsRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(
            repository) as T
    }

}