package com.fatec.fateats2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.fatec.fateats2.dao.ProductDao
import com.fatec.fateats2.model.Product
import com.fatec.fateats2.sampledata.sampleCandies
import com.fatec.fateats2.sampledata.sampleProducts
import com.fatec.fateats2.sampledata.sampleSections
import com.fatec.fateats2.ui.activity.ProductFormActivity
import com.fatec.fateats2.ui.components.CardProductItem
import com.fatec.fateats2.ui.components.CustomTopAppBar
import com.fatec.fateats2.ui.components.ProductItem
import com.fatec.fateats2.ui.components.ProductsSection
import com.fatec.fateats2.ui.components.SearchTextField
import com.fatec.fateats2.ui.components.SearchTextFieldPreview
import com.fatec.fateats2.ui.screens.HomeScreen
import com.fatec.fateats2.ui.theme.Fateats2Theme
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App(
                onFabClick = {
                    startActivity(
                        Intent(
                            this,
                            ProductFormActivity::class.java
                        )
                    )
                }
            )
        }
    }

    @Composable
    private fun App(
        onFabClick: () -> Unit = {},
    ) {
        Fateats2Theme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = onFabClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            )
            { innerPadding ->
                Box(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    val products = dao.products()
                    HomeScreen(products = sampleProducts )
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