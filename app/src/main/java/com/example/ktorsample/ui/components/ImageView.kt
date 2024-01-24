package com.example.ktorsample.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter

@Composable
fun ImageView(
    modifier: Modifier = Modifier,
    url: String
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = rememberImagePainter(url),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun PreviewImageView() {
    ImageView(url = "temp")
}