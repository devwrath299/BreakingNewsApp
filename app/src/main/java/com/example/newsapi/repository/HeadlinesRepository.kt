package com.example.newsapi.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapi.R
import com.example.newsapi.api.ApiInterface
import com.example.newsapi.api.RetrofitHelper
import com.example.newsapi.db.ArticleDatabase
import com.example.newsapi.model.Article
import com.example.newsapi.model.TopHeadLineResponse
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.*


class HeadlinesRepository(
    private val applicationContext: Context,
    val db: ArticleDatabase
) {
    private val headlineLiveData = MutableLiveData<TopHeadLineResponse>()
    val headlines: LiveData<TopHeadLineResponse>
        get() = headlineLiveData

    suspend fun getHeadlineRespose(country: String) {
        val result = RetrofitHelper.api.getHeadlines(country)
        if (result.body() != null) {
            headlineLiveData.postValue(result.body())
        }

    }
    suspend fun  getBreakingNews(country: String,pageNumber:Int)=
        RetrofitHelper.api.getBreakingNews(country,pageNumber)

    suspend fun  searchNews(searchQuery:String,pageNumber: Int)=
        RetrofitHelper.api.searchNews(searchQuery,pageNumber)

    suspend fun  upsert(article: Article)=db.getArticleDao().upsertArticle(article)

    fun getSavedNews()=db.getArticleDao().getAllArticles()

    suspend fun  deleteArticle(article: Article)=db.getArticleDao().deleteArticle(article)






}