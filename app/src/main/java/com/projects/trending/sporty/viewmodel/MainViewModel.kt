package com.projects.trending.sporty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.trending.sporty.Repository
import com.projects.trending.sporty.utils.Resource
import com.projects.trending.sporty.models.NewsModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository)   : ViewModel(){

    val breakingNews: MutableLiveData<Resource<NewsModel>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsModel? = null


    init {
        getTopNews("us")
    }


    // View Model scope will make sure our coroutine will be alive as long as our view model is alive
    fun getTopNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())   // for loading the screen
        val response = repository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsModel>) : Resource<NewsModel> {
        if(response.isSuccessful) {
            // if response body is not null
            response.body()?.let { resultResponse ->
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}