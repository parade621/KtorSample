package com.example.ktorsample.ui.screens

import android.view.SearchEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktorsample.ui.components.SearchBar

@Preview
@Composable
fun HomeScreen() {

}

@Preview
@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    val query = remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "News",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.White
                )
            )

            SearchBar(
                hint = "Search",
                text = query.value,
                modifier = Modifier,
                focusRequester = FocusRequester(),
                visualTransformation = VisualTransformation.None,
                getNewString = { newText ->
                    query.value = newText
                }
            ) {
                // TODO: Implement search event
            }
        }
    }
}