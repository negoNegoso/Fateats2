package com.fatec.fateats2.ui.screens

import android.media.midi.MidiDeviceInfo.PortInfo
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fatec.fateats2.model.Product
import com.fatec.fateats2.sampledata.sampleCandies
import com.fatec.fateats2.sampledata.sampleDrinks
import com.fatec.fateats2.sampledata.sampleProducts
import com.fatec.fateats2.ui.components.CustomTopAppBar
import com.fatec.fateats2.ui.components.ProductsSection
import com.fatec.fateats2.ui.components.SearchTextField

class HomeScreenUiState(
    val sectios: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}

@Composable
fun HomeScreen(products: List<Product> = sampleProducts) {
    val sectios = mapOf(
        "Todos Produtos" to products,
        "Promoção" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )
    var text by remember { mutableStateOf("") }

    Column()
    {
        CustomTopAppBar(title = "Home")

        SearchTextField(
            searchText = text,
            onSearchChange = { newText ->
                text = newText
                Log.i("HomeScreen", "HomeScreen: TextField: $newText")
                Log.i("HomeScreen", "HomeScreen: state: $text")
            })

        ProductsSection(
            "Promoções",
            products = products
        )
    }
}