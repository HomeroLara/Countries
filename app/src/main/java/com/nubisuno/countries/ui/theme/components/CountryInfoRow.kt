package com.nubisuno.countries.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nubisuno.countries.models.Country
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter


@Composable
fun CountryInfoRow(
    modifier: Modifier = Modifier,
    country: Country,
    navController: NavController
) {

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    // Navigate to details screen when row is tapped
                    navController.navigate ("details/${country.name.common}")
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "${country.name.common}",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 2.dp)
            )

            if(country.flagPng.isNotEmpty()) {
                val imagePainter = rememberAsyncImagePainter(country.flagPng)
                Image(
                    painter = imagePainter,
                    contentDescription = "Flag",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoRowPreview() { }
