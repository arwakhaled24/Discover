package com.example.feature.newslist.screens

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.domain.entity.Article

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsSuccessScreen(
    newsList: List<Article>,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onItemClick: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedArticleUrl by remember { mutableStateOf("") }
    val context = LocalContext.current

    if (showDialog) {
        NewsItemChoiceDialog(
            articleUrl = selectedArticleUrl,
            onDismissRequest = { showDialog = false },
            onOpenInApp = { url ->
                showDialog = false
                onItemClick(url)
            },
            onOpenInBrowser = { url ->
                showDialog = false
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Discover",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "News from all around the world about ${query} ",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 2.dp)
        )

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(25.dp),
            trailingIcon = {
                IconButton(onClick = onSearch) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            )
        )

        if (newsList.isNotEmpty()) {
            LazyColumn {
                items(newsList.size) { index ->
                    NewsCard(
                        article = newsList[index],
                        onClick = {
                            selectedArticleUrl = newsList[index].url
                            showDialog = true
                        },
                    )
                }
            }
        } else {
            Text(
                text = "Oops! No news about \"$query\" even the journalists are surprised! \uD83D\uDD75\uFE0F Try a new search?",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
