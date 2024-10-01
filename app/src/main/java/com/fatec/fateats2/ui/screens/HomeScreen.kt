package com.fatec.fateats2.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fatec.fateats2.sampledata.sampleProducts
import com.fatec.fateats2.ui.components.CustomTopAppBar
import com.fatec.fateats2.ui.components.ProductsSection
import com.fatec.fateats2.ui.components.SearchTextField

@Composable
fun HomeScreen() {
    Column()
    {
        CustomTopAppBar(title = "Home")
        var text by remember { mutableStateOf("") }
        SearchTextField(
            searchText = text,
            onSearchChange = { newText ->
                text = newText
                Log.i("HomeScreen", "HomeScreen: TextField: $newText")
                Log.i("HomeScreen", "HomeScreen: state: $text")
            })

        ProductsSection(
            "Promoções",
            products = sampleProducts
        )
    }
}