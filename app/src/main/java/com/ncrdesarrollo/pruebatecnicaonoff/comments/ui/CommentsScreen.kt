package com.ncrdesarrollo.pruebatecnicaonoff.comments.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsEntity
import com.ncrdesarrollo.pruebatecnicaonoff.core.utils.UiState
import com.ncrdesarrollo.pruebatecnicaonoff.ui.theme.PruebaTecnicaONOFFTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    modifier: Modifier,
    viewModel: CommentsViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.commentsList.collectAsState()

    Scaffold(
        modifier = modifier.padding(),
        topBar = {
            TopAppBar(
                title = { Text("Comentarios") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        },
        bottomBar = {
            MessageInput(
                onSendMessage = { message ->
                    viewModel.insertComment(message)
                }
            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is UiState.Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            is UiState.Success -> CommentsList(
                comments = state.data,
                modifier = Modifier.padding(paddingValues)
            )

            is UiState.Error -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text(state.message) }

            UiState.Empty -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text("No hay comentarios de esta publicación") }
        }

    }
}

@Composable
fun CommentsList(comments: List<CommentsEntity>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(comments, key = { it.id }) { comment ->
            CommentItem(comment = comment)
        }
    }
}

@Composable
fun MessageInput(
    onSendMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Escribe un comentario...") },
            maxLines = 3
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                if (text.isNotBlank()) {
                    onSendMessage(text)
                    text = ""
                }
            },
            enabled = text.isNotBlank() 
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Enviar comentario",
                tint = if (text.isNotBlank()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.5f
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CommentsScreenPreview() {
    PruebaTecnicaONOFFTheme {
        val list = listOf(
            CommentsEntity(1, 1, "Este es un comentario de prueba"),
            CommentsEntity(2, 2, "Este es un comentario de prueba 2"),

        )
        CommentsList(list)
    }
}

@Preview(showBackground = true)
@Composable
fun MessageInputPreview() {
    PruebaTecnicaONOFFTheme {
        MessageInput(onSendMessage = {})
    }
}
