package com.app.kmp_app.data.remote

import com.app.kmp_app.model.NewsArticle
import com.app.kmp_app.model.NewsResponse
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import kmp_app.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

class RemoteDataSource(private val httpClient: HttpClient) {
    
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun getTrendingNews(): List<NewsArticle> {
        val bytes = Res.readBytes("files/trendyresult.json")
        val jsonString = bytes.decodeToString()
        val response = json.decodeFromString<NewsResponse>(jsonString)
        return response.articles
    }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun getSearchDummyData(): List<NewsArticle> {
        val bytes = Res.readBytes("files/searchresultdummydata.json")
        val jsonString = bytes.decodeToString()
        val response = json.decodeFromString<NewsResponse>(jsonString)
        return response.articles
    }
}
