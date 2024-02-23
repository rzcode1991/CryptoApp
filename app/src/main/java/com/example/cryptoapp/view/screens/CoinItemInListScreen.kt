package com.example.cryptoapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.cryptoapp.model.data.dto2.list.CoinInfo
import com.example.cryptoapp.view.ui.theme.MyBrown
import com.example.cryptoapp.view.ui.theme.MyCreamy

@Composable
fun CoinItemInListScreen(
    coinIndex: Int,
    coin: CoinInfo,
    price: String,
    modifier: Modifier = Modifier
){

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MyCreamy)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${coinIndex+1}.${coin.FullName} (${coin.Name})",
            color = MyBrown
        )
        Text(
            text = price,
            color = MyBrown
        )
    }

}
