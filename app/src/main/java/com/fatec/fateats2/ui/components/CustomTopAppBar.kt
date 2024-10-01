package com.fatec.fateats2.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.fatec.fateats2.ui.theme.Indigo500
import com.fatec.fateats2.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title: String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Indigo500,
            titleContentColor = White
        ),
        title = {
            Text(text = title)
        }
    )
}