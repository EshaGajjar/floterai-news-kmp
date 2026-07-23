package com.app.kmp_app.data.local

import com.app.kmp_app.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.app.kmpapp.database.SearchHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock

class LocalDataSource(private val database: AppDatabase) {
    private val queries = database.appDatabaseQueries

    fun getSearchHistory(): Flow<List<SearchHistory>> {
        return queries.getRecentSearches().asFlow().mapToList(Dispatchers.Default)
    }

    suspend fun insertSearch(query: String) {
        queries.insertSearch(query, Clock.System.now().toEpochMilliseconds())
    }

    suspend fun deleteSearch(query: String) {
        queries.deleteSearch(query)
    }

    suspend fun clearHistory() {
        queries.clearHistory()
    }
}
