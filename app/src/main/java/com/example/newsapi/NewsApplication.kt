package com.example.newsapi

import android.app.Application
import com.example.newsapi.api.ApiInterface
import com.example.newsapi.api.RetrofitHelper
import com.example.newsapi.db.ArticleDatabase
import com.example.newsapi.repository.HeadlinesRepository

class NewsApplication:Application() {
    lateinit var  repository: HeadlinesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
//        val apiInterface= RetrofitHelper.getInstance().create(ApiInterface::class.java);
        repository=HeadlinesRepository(applicationContext,ArticleDatabase(this))
    }
}