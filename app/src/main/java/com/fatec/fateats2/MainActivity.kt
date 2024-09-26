package com.fatec.fateats2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fatec.fateats2.model.Product
import com.fatec.fateats2.sampledata.sampleProducts
import com.fatec.fateats2.ui.components.CardProductItem
import com.fatec.fateats2.ui.components.ProductItem
import com.fatec.fateats2.ui.components.ProductsSection
import com.fatec.fateats2.ui.components.SearchTextField
import com.fatec.fateats2.ui.components.SearchTextFieldPreview
import com.fatec.fateats2.ui.theme.Fateats2Theme
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Fateats2Theme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    Column(
                        modifier = Modifier.padding(
                            innerPadding
                        )
                    )
                    {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        var text by remember { mutableStateOf("Default") }
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
                        ProductItem(
                            product = Product(
                                name = "teste",
                                price = BigDecimal("9.99")
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Fateats2Theme {
        Greeting("Android")
    }
}