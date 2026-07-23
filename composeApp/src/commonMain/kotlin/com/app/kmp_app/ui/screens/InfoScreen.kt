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
                                This application is a News Aggregator built with Kotlin Multiplatform and Compose Multiplatform.
                                
                                Features:
                                - Trending news from the Metropolitan Museum of Art (initially JSON).
                                - Search functionality with debouncing.
                                - Local search history using SQLDelight.
                                - Responsive UI with Material 3.
                                - Dark mode support.
                                - Platform-specific URL opening using Chrome Custom Tabs on Android.
                                
                                Architecture:
                                The app follows Clean Architecture principles with MVVM pattern.
                                - Data Layer: Repository pattern with Local and Remote data sources.
                                - Domain Layer: Models and Repository interfaces.
                                - UI Layer: Compose Multiplatform with Voyager for navigation.
                                - Dependency Injection: Koin for KMP.
                                
                                This assessment demonstrates proficiency in:
                                1. Kotlin Multiplatform (KMP)
                                2. Compose Multiplatform
                                3. Clean Architecture & SOLID principles
                                4. Advanced State Management with Flow/StateFlow
                                5. Local Database (SQLDelight)
                                6. Networking (Ktor)
                                7. Dependency Injection (Koin)
                                
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                
                                Additional content to make it a long scrollable text as requested:
                                Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
                                
                            """.trimIndent(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
