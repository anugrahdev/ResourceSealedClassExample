package com.anugrahdev.resourcesealedclassexample.network

import com.anugrahdev.resourcesealedclassexample.models.NewsResponse

class NewsRepository(private val api:ApiService) {

    suspend fun getNews(): NewsResponse {
        val response = api.getTopHeadlines()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception(response.message())
        }
    }
}