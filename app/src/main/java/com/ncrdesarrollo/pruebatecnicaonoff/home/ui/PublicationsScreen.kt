package com.ncrdesarrollo.pruebatecnicaonoff.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import com.ncrdesarrollo.pruebatecnicaonoff.core.utils.UiState
import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.model.Publication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublicationsScreen(
    modifier: Modifier,
    viewModel: PublicationsViewModel,
    navigateToComments: (Int) -> Unit
) {

    val uiState by viewModel.publicationsList.collectAsState()
    var searchText by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("Publicaciones") })
                SearchBar(
                    searchText = searchText,
                    onSearchTextChanged = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Success -> {
                    PublicationsList(
                        publications = state.data,
                        searchText = searchText
                    ) { id ->
                        navigateToComments(id)
                    }
                }

                is UiState.Error -> {
                    Text(text = "Error: ${state.message}")
                }

                UiState.Empty -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "No hay publicaciones almacenadas")
                        Button(onClick = { viewModel.getPublicationsApi() }) {
                            Text(text = "Consultar API")
                        }
                    }

                }
            }
        }
    }

}

@Composable
fun PublicationsList(
    publications: List<Publication>,
    searchText: String = "",
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(publications.filter {
            it.title.contains(
                searchText,
                ignoreCase = true
            ) || it.id.toString().contains(searchText, ignoreCase = true)
        }, key = { it.id }) { publication ->
            PublicationItem(publication = publication) { id ->
                onItemClick(id)
            }
        }
    }
}


