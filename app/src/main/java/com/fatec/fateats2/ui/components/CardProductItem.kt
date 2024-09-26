package com.fatec.fateats2.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fatec.fateats2.R
import com.fatec.fateats2.extensions.toBrazilianCurrency
import com.fatec.fateats2.model.Product
import com.fatec.fateats2.ui.theme.Fateats2Theme
import com.fatec.fateats2.ui.theme.Indigo400Light
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(150.dp),
        elevation = CardDefaults.cardElevation(elevation)
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(
                    id = R.drawable.placeholder
                ),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Indigo400Light)
                    .padding(16.dp)
            ) {
                Text(text = product.name, color = Color.White)
                Text(text = product.price.toBrazilianCurrency(), color = Color.White)
            }
            product.description?.let {
                Text(
                    text = product.description,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CardProductItemPreview() {
    Fateats2Theme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "teste",
                    price = BigDecimal("9.99"),
                    ),
            )
        }
    }
}
@Preview
@Composable
fun CardProductItemDescriptionPreview() {
    Fateats2Theme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "teste",
                    price = BigDecimal("9.99"),
                    description = "Chocolate Lacta Oreo é a novidade que une o delicioso chocolate Lacta ao leite com um recheio cremoso e irresistivel de baunilha e cheio de pedacinhos de biscoito Oreo, que promete te surpreender. Em seu formato de 90g, ele é perfeito para você que ama Lacta e Oreo se deliciar a qualquer momento.\n"
                ),
            )
        }
    }
}