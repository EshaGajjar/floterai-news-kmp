package com.app.kmp_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import coil3.compose.AsyncImage
import com.app.kmp_app.model.NewsArticle
import com.app.kmp_app.interfaces.PlatformAction
import network.chaintech.sdpcomposemultiplatform.sdp
import org.koin.compose.koinInject

data class ArticleDetailScreen(val article: NewsArticle) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val platformAction = koinInject<PlatformAction>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.sdp)
                .verticalScroll(rememberScrollState())
        ) {
            // Drag handle and close button
            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .size(40.sdp, 4.sdp)
                        .clip(RoundedCornerShape(2.sdp))
                        .background(MaterialTheme.colorScheme.outlineVariant)
                        .align(Alignment.Center)
                )
                
                IconButton(
                    onClick = { bottomSheetNavigator.hide() },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(8.sdp))

            AsyncImage(
                model = article.urlToImage,
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.sdp)
                    .clip(RoundedCornerShape(16.sdp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.sdp))
            
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.sdp))
            
            Text(
                text = article.author?.let { "By $it" } ?: article.source?.name ?: "Unknown",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            
            Spacer(modifier = Modifier.height(16.sdp))
            
            Text(
                text = "Description",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = article.description ?: "No description available.",
                style = MaterialTheme.typography.bodyLarge
            )
            
            article.content?.let {
                Spacer(modifier = Modifier.height(16.sdp))
                Text(
                    text = "Content",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(24.sdp))
            
            Button(
                onClick = { 
                    article.url?.let { platformAction.openUrl(it) }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.sdp),
                enabled = article.url != null
            ) {
                Text("View Article")
            }
            
            Spacer(modifier = Modifier.height(32.sdp))
        }
    }
}
