package com.projects.trending.sporty.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.projects.trending.sporty.data.Repository
import com.projects.trending.sporty.models.Article
import com.projects.trending.sporty.utils.Resource
import com.projects.trending.sporty.models.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( private val repository: Repository,
                     application: Application
)   : AndroidViewModel(application){

    // Retrofit
    val breakingNews: MutableLiveData<Resource<NewsModel>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsModel? = null




    init {
        getTopNews("sport")
    }


    // View Model scope will make sure our coroutine will be alive as long as our view model is alive
    fun getTopNews(query: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())   // for loading the screen
        val response = repository.remote.getBreakingNews(query, breakingNewsPage)
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

    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.local.upsert(article)
    }

    fun getSavedNews() = repository.local.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        repository.local.deleteArticle(article)
    }

//    fun isDataExist(url : String) =  viewModelScope.launch {
//        repository.isDataExist(url)
//    }

}