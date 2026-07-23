package com.app.kmp_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.kmp_app.data.repository.NewsRepository
import com.app.kmpapp.database.SearchHistory
import com.app.kmp_app.model.NewsArticle
import com.app.kmp_app.ui.state.UiState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<NewsArticle>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<NewsArticle>>> = _uiState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _searchResults = MutableStateFlow<UiState<List<NewsArticle>>>(UiState.Empty)
    val searchResults: StateFlow<UiState<List<NewsArticle>>> = _searchResults

    val searchHistory: StateFlow<List<SearchHistory>> = repository.getSearchHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        loadNews()
        observeSearchQuery()
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        searchQuery
            .debounce(500)
            .distinctUntilChanged()
            .onEach { query ->
                if (query.isNotBlank()) {
                    performSearch(query, saveToHistory = false)
                } else {
                    _searchResults.value = UiState.Empty
                }
            }
            .launchIn(viewModelScope)
    }

    fun loadNews() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val news = repository.getTrendingNews()
                if (news.isEmpty()) {
                    _uiState.value = UiState.Empty
                } else {
                    _uiState.value = UiState.Success(news)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun onQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun performSearch(query: String, saveToHistory: Boolean = true) {
        if (query.isBlank()) return
        _searchQuery.value = query
        viewModelScope.launch {
            _searchResults.value = UiState.Loading
            try {
                val results = repository.searchNews(query)
                if (results.isEmpty()) {
                    _searchResults.value = UiState.Empty
                } else {
                    _searchResults.value = UiState.Success(results)
                    if (saveToHistory) {
                        repository.addSearchHistory(query)
                    }
                }
            } catch (e: Exception) {
                _searchResults.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun deleteHistory(query: String) {
        viewModelScope.launch {
            repository.deleteSearchHistory(query)
        }
    }
}
