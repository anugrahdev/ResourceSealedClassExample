package com.anugrahdev.resourcesealedclassexample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrahdev.resourcesealedclassexample.models.Article
import com.anugrahdev.resourcesealedclassexample.network.NewsRepository
import com.anugrahdev.resourcesealedclassexample.vo.Resource
import kotlinx.coroutines.launch
import java.io.IOException

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _headlineNews = MutableLiveData<Resource<List<Article>>>()
    val headlineNews: LiveData<Resource<List<Article>>> get() = _headlineNews

    init {
        getHeadlineNews()
    }

    fun getHeadlineNews() = viewModelScope.launch {
        _headlineNews.postValue(Resource.Loading())
        try{
            val response = repository.getNews()
            _headlineNews.postValue(Resource.Success(response.articles))
        }catch (e: IOException){
            _headlineNews.postValue(Resource.Error(e.message))
        }

    }

}