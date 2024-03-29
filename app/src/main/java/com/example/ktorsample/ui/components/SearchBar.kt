package com.example.ktorsample.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PreviewLoginTextField() {
    SearchBar(
        hint = "Search",
        modifier = Modifier,
        focusRequester = FocusRequester(),
        getNewString = { },
        visualTransformation = VisualTransformation.None
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    hint: String,
    text: String = "",
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    visualTransformation: VisualTransformation,
    getNewString: (String) -> Unit,
    onEvent: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newValue ->
            getNewString(newValue)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp),
            )
            .focusRequester(focusRequester = focusRequester),
        textStyle = TextStyle(
            fontSize = 20.sp,
            lineHeight = 30.sp,
            color = Color.Black
        ),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                modifier = Modifier
                    .clickable {
                        onEvent(text)
                    }
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Mic Icon",
                modifier = Modifier
                    .clickable {
                        // 음성 검색 기능 추가
                    }
            )

        },
        placeholder = {
            Text(
                text = hint,
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        visualTransformation = visualTransformation,
        keyboardActions = KeyboardActions(onSearch = { // '검색' 버튼 액션 정의
            onEvent(text)
        })
    )
}