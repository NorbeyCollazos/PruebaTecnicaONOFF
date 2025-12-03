package com.ncrdesarrollo.pruebatecnicaonoff.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.model.Publication
import com.ncrdesarrollo.pruebatecnicaonoff.ui.theme.PruebaTecnicaONOFFTheme

@Composable
fun PublicationItem(
    publication: Publication,
    modifier: Modifier = Modifier,
    onClickListener: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClickListener(publication.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = publication.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = publication.body,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PublicationItemPreview() {
    PruebaTecnicaONOFFTheme {
        val samplePublication = Publication(
            id = 1,
            title = "Este es un título de ejemplo muy largo para ver cómo se corta",
            body = "Este es el contenido de la publicación. Es un texto un poco más largo para demostrar cómo el componente maneja el desbordamiento de texto con elipsis después de tres líneas."
        )
        PublicationItem(publication = samplePublication){}
    }
}

