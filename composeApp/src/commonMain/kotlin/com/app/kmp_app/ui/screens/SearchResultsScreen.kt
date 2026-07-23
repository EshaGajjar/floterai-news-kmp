package com.app.kmp_app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.app.kmp_app.ui.components.*
import com.app.kmp_app.ui.state.UiState
import com.app.kmp_app.viewmodel.HomeViewModel
import network.chaintech.sdpcomposemultiplatform.sdp
import org.koin.compose.viewmodel.koinViewModel

data class SearchResultsScreen(val query: String) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val viewModel = koinViewModel<HomeViewModel>()
        val searchResults by viewModel.searchResults.collectAsState()

        LaunchedEffect(query) {
            viewModel.performSearch(query)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Results for \"$query\"") },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (val state = searchResults) {
                    is UiState.Loading -> LoadingShimmer()
                    is UiState.Success -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                        onRetry = { viewModel.performSearch(query) }
                    )
                    is UiState.Empty -> EmptyState("No results found for \"$query\"")
                }
            }
        }
    }
}
