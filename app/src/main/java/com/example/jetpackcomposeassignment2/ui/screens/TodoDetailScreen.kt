package com.example.jetpackcomposeassignment2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeassignment2.model.Todo
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(todo: Todo, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Todo Detail",
                        style = MaterialTheme.typography.headlineSmall, // Changed style
                        color = MaterialTheme.colorScheme.secondary // Different color
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.tertiary // Changed icon color
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant // Changed bar color
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp), // More padding
            horizontalAlignment = Alignment.CenterHorizontally, // Center content
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top) // More spacing
        ) {
            Text(
                text = "🆔 ID: ${todo.id}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "👤 User ID: ${todo.userId}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "📌 Title:\n${todo.title}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = if (todo.completed) "✅ Completed" else "❌ Incomplete",
                style = MaterialTheme.typography.titleMedium,
                color = if (todo.completed) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}
