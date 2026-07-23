package com.app.kmp_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen
import network.chaintech.sdpcomposemultiplatform.sdp

class InfoScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.sdp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "About News Aggregator",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.sdp))
            Text(
                text = """
Welcome to News Aggregator

News Aggregator is a modern news application designed to help you discover and read the latest headlines in a simple, fast, and intuitive way. Whether you're interested in technology, business, sports, entertainment, or world news, the app provides an organized reading experience with powerful search capabilities.

Features

• Browse the latest trending news.
• Search articles using keywords.
• View and reuse your recent search history.
• Pull down to refresh the latest headlines.
• Preview article details before opening.
• Open full articles in your preferred web browser.
• Responsive interface for different screen sizes.
• Clean Material Design 3 user experience.

Getting Started

Home

The Home screen displays the latest trending news. Simply scroll through the feed and tap on any article to view more details.

Search

Use the search bar to find articles related to any topic. Recent searches are stored locally so you can quickly access them again without retyping.

Article Details

Selecting an article opens a preview dialog containing the article title, summary, and a button to open the complete article in your browser.

Refresh

Pull down on the Home screen to refresh the feed and retrieve the latest available news.

Privacy

Your recent search history is stored locally on your device to improve your experience. The application does not collect or share personal information.

Technology

This application is built using modern Kotlin Multiplatform technologies and follows the MVVM architectural pattern.

Core technologies include:

• Kotlin Multiplatform (KMP)
• Compose Multiplatform
• MVVM Architecture
• Ktor Networking
• SQLDelight Local Database
• Koin Dependency Injection
• Voyager Navigation
• Kotlin Coroutines & StateFlow
• Material Design 3

Our Goal

News Aggregator aims to provide a fast, reliable, and enjoyable reading experience while demonstrating modern mobile development practices, clean architecture, and responsive user interface design.

Thank you for using News Aggregator.

Stay informed.
Stay curious.
Stay connected.

Version 1.0.0
    """.trimIndent(),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(84.sdp))
        }
    }
}
