package com.app.kmp_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.app.kmp_app.ui.components.*
import com.app.kmp_app.ui.state.UiState
import com.app.kmp_app.viewmodel.HomeViewModel
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.koin.compose.viewmodel.koinViewModel

class HomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val viewModel = koinViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val searchQuery by viewModel.searchQuery.collectAsState()
        val searchHistory by viewModel.searchHistory.collectAsState()
        val searchResults by viewModel.searchResults.collectAsState()

        var isSearchActive by remember { mutableStateOf(value = false) }

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = 24.sdp)
        ) {
            @Suppress("DEPRECATION")
            SearchBar(
                query = searchQuery,
                onQueryChange = { viewModel.onQueryChange(it) },
                onSearch = {
                    viewModel.performSearch(it)
                    isSearchActive = false
                    navigator.push(SearchResultsScreen(it))
                },
                active = isSearchActive,
                onActiveChange = { isSearchActive = it },
                placeholder = { Text("Search news...") },
                leadingIcon = {
                    if (isSearchActive) {
                        IconButton(onClick = { isSearchActive = false }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    } else {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.onQueryChange("") }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = if (isSearchActive) 0.sdp else 16.sdp,
                        vertical = 0.sdp
                    )
            ) {
                if (searchQuery.isEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        items(searchHistory) { history ->
                            ListItem(
                                headlineContent = { Text(history.query) },
                                leadingContent = {
                                    Icon(
                                        Icons.Default.History,
                                        contentDescription = null
                                    )
                                },
                                trailingContent = {
                                    IconButton(onClick = { viewModel.deleteHistory(history.query) }) {
                                        Icon(Icons.Default.Close, contentDescription = "Delete")
                                    }
                                },
                                modifier = Modifier.clickable {
                                    viewModel.onQueryChange(history.query)
                                    isSearchActive = false
                                    navigator.push(SearchResultsScreen(history.query))
                                }
                            )
                        }
                    }
                } else {
                    when (val state = searchResults) {
                        is UiState.Loading -> LoadingShimmer()
                        is UiState.Success -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.surface)
                            ) {
                                items(state.data) { article ->
                                    ArticleCard(
                                        article = article,
                                        onClick = {
                                            bottomSheetNavigator.show(ArticleDetailScreen(article))
                                        }
                                    )
                                }
                            }
                        }

                        is UiState.Error -> ErrorState(
                            state.message,
                            onRetry = { viewModel.performSearch(searchQuery) }
                        )

                        is UiState.Empty -> EmptyState("No results found for \"$searchQuery\"")
                    }
                }
            }

            if (!isSearchActive) {
                PullToRefreshBox(
                    isRefreshing = false,
                    onRefresh = { viewModel.loadNews() }
                ) {
                    when (val state = uiState) {
                        is UiState.Loading -> LoadingShimmer()
                        is UiState.Success -> {
                            val news = state.data
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(news) { article ->
                                    ArticleCard(
                                        article = article,
                                        onClick = {
                                            bottomSheetNavigator.show(ArticleDetailScreen(article))
                                        }
                                    )
                                }
                            }
                        }

                        is UiState.Error -> ErrorState(
                            state.message,
                            onRetry = { viewModel.loadNews() }
                        )

                        is UiState.Empty -> EmptyState()
                    }
                }
            }
        }
    }
}
