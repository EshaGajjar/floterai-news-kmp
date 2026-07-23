package com.app.kmp_app.data.repository

import com.app.kmp_app.data.local.LocalDataSource
import com.app.kmp_app.data.remote.RemoteDataSource
import com.app.kmpapp.database.SearchHistory
import com.app.kmp_app.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTrendingNews(): List<NewsArticle>
    suspend fun searchNews(query: String): List<NewsArticle>
    fun getSearchHistory(): Flow<List<SearchHistory>>
    suspend fun addSearchHistory(query: String)
    suspend fun deleteSearchHistory(query: String)
}

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {

    override suspend fun getTrendingNews(): List<NewsArticle> {
        return remoteDataSource.getTrendingNews()
    }

    override suspend fun searchNews(query: String): List<NewsArticle> {
        val searchData = remoteDataSource.getSearchDummyData()
        if (query.isEmpty()) return searchData
        return searchData.filter { 
            it.title.contains(query, ignoreCase = true) || 
            (it.description?.contains(query, ignoreCase = true) == true)
        }
    }

    override fun getSearchHistory(): Flow<List<SearchHistory>> {
        return localDataSource.getSearchHistory()
    }

    override suspend fun addSearchHistory(query: String) {
        if (query.isNotBlank()) {
            localDataSource.insertSearch(query)
        }
    }

    override suspend fun deleteSearchHistory(query: String) {
        localDataSource.deleteSearch(query)
    }
}
