package com.fatec.fateats2.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fatec.fateats2.model.Product
import com.fatec.fateats2.sampledata.sampleProducts
import com.fatec.fateats2.ui.theme.Fateats2Theme
import com.fatec.fateats2.ui.theme.Indigo400Light

@Composable
fun ProductsSection(
    title: String,
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        var showSection by remember { mutableStateOf(true) }

        if (showSection) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 8.dp
                        ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(400)
                )
                Text(
                    text = "(" + products.count().toString() + " Produtos)",
                    modifier = Modifier
                        .padding(
                            end = 16.dp
                        ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(400)
                )
                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(Indigo400Light),
                    onClick = { showSection = !showSection } // Mostrar pesquisa
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = null
                    )
                }
            }
            LazyRow(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(products) { product ->
                    ProductItem(product = product)
                }
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 8.dp
                        ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(400)
                )
                Text(
                    text = "(" + products.count().toString() + " Produtos)",
                    modifier = Modifier
                        .padding(
                            end = 16.dp
                        ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(400)
                )
                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(Indigo400Light),
                    onClick = { showSection = !showSection } // Mostrar pesquisa
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsSectionPreview() {
    Fateats2Theme {
        Surface {
            ProductsSection(
                "Promoções",
                products = sampleProducts
            )
        }
    }
}