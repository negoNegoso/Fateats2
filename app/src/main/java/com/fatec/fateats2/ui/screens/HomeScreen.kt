package com.fatec.fateats2.ui.screens

import android.media.midi.MidiDeviceInfo.PortInfo
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fatec.fateats2.model.Product
import com.fatec.fateats2.sampledata.sampleCandies
import com.fatec.fateats2.sampledata.sampleDrinks
import com.fatec.fateats2.sampledata.sampleProducts
import com.fatec.fateats2.sampledata.sampleSections
import com.fatec.fateats2.ui.components.CardProductItem
import com.fatec.fateats2.ui.components.CustomTopAppBar
import com.fatec.fateats2.ui.components.ProductsSection
import com.fatec.fateats2.ui.components.SearchTextField
import com.fatec.fateats2.ui.theme.Fateats2Theme
import com.fatec.fateats2.ui.theme.Indigo400Light
import com.fatec.fateats2.ui.theme.Indigo500

class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
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
    val sections = mapOf(
        "Todos Produtos" to products,
        "Promoção" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )
    var text by remember { mutableStateOf("") }

    fun containsInNameOrDescrioption() = { product: Product ->
        product.name.contains(text, ignoreCase = true) ||
                product.description?.contains(text, ignoreCase = true) ?: false
    }

    val searchedProducts = remember(text, products) {
        if (text.isNotBlank()) {
            (sampleProducts + products).filter(containsInNameOrDescrioption())
        } else emptyList()
    }

    val state = remember(text, products) {
        HomeScreenUiState(
            sections = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = {
                text = it
            }
        )
    }

    HomeScreen(state = state)

}

@Composable
fun HomeScreen(state: HomeScreenUiState) {
    Column {
        val sections = state.sections
        var text = state.searchText
        val searchedProducts = state.searchedProducts

        CustomTopAppBar(title = "Home")

        SearchTextField(
            searchText = text,
            onSearchChange = state.onSearchChange,
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        // Controlando a visibilidade das listas
        var showLists by remember { mutableStateOf(true) }
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Mostrar Listas:")
            Button(
                onClick = { showLists = true },
                colors = ButtonDefaults.buttonColors(Indigo500)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
            Button(
                onClick = { showLists = false },
                colors = ButtonDefaults.buttonColors(Indigo500),
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = null
                )
            }
        }
        // Controlando a visibilidade das listas com showSearchedList
        var showSearchedList by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            OutlinedButton(
                colors = ButtonDefaults.buttonColors(Indigo500),
                onClick = { showSearchedList = false } // Mostrar seções
            ) {
                Text(text = "Section")
            }

            OutlinedButton(
                enabled = searchedProducts.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(Indigo400Light),
                onClick = { showSearchedList = true } // Mostrar pesquisa
            ) {
                Text(text = "Searched")
            }
        }

        if (showLists) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                if (showSearchedList) {
                    items(searchedProducts) { product ->
                        CardProductItem(
                            product = product,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                } else {
                    for (section in sections) {
                        val title = section.key
                        val product = section.value
                        item {
                            ProductsSection(
                                title = title,
                                products = product
                            )
                        }
                    }
                }
            }
        }else{
            Text(text = "SEM PRODUTOS")
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Fateats2Theme {
        Surface {
            HomeScreen(
                state = HomeScreenUiState(
                    sections = sampleSections
                )
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenWithSearchTextPreview() {
    Fateats2Theme {
        Surface {
            HomeScreen(
                state = HomeScreenUiState(
                    searchText = "c",
                    sections = sampleSections
                )
            )
        }
    }
}