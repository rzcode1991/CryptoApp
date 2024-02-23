package com.example.cryptoapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.cryptoapp.model.data.dto2.detail.Data
import com.example.cryptoapp.view.ui.theme.MyBlue
import com.example.cryptoapp.view.ui.theme.MyBrown
import com.example.cryptoapp.view.ui.theme.MyCreamy
import com.example.cryptoapp.view.ui.theme.MyGreen
import com.example.cryptoapp.view.ui.theme.MyPink
import com.example.cryptoapp.viewModel.CoinDetailViewModel

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
    symbol: String
) {

    viewModel.getCoinDetail(symbol)
    val coinFullDetail = viewModel.coinFullDetail.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyCreamy)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        coinFullDetail.value?.let { coinDetail ->
            val coinData = coinDetail?.Data
            TopText(coinData)
            CoinLogo(coinData)
            Description(coinData)
            LeadersInfo(coinData)
            AlgorithmDetails(coinData)
        }
    }


}


@Composable
fun TopText(coinData: Data?) {
    Text(
        text = "${coinData?.NAME} (${coinData?.SYMBOL})",
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = MyBrown
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CoinLogo(coinData: Data?) {
    val uriHandler = LocalUriHandler.current
    GlideImage(
        model = coinData?.LOGO_URL,
        contentDescription = "",
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                uriHandler.openUri(coinData?.WEBSITE_URL ?: "")
            }
    )
}


@Composable
fun Description(coinData: Data?) {
    SelectionContainer {
        Text(
            text = coinData?.ASSET_DESCRIPTION_SNIPPET ?: "err",
            color = MyPink
        )
    }
}


@Composable
fun LeadersInfo(coinData: Data?) {
    if (coinData?.PROJECT_LEADERS != null) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = "Project Leaders",
                color = MyBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            for (leader in coinData.PROJECT_LEADERS) {
                Column(

                ) {
                    Text(
                        text = leader.FULL_NAME ?: "",
                        color = MyBlue
                    )
                    Text(
                        text = leader.LEADER_TYPE ?: "",
                        color = MyBlue,
                        fontStyle = FontStyle.Italic
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White)
                    ){

                    }
                }
            }
        }
    }
}


@Composable
fun AlgorithmDetails(coinData: Data?) {
    if (coinData?.CONSENSUS_ALGORITHM_TYPES != null) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MyGreen)
        ) {
            Text(
                text = "Algorithm Type",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MyBrown,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
            for (algorithm in coinData.CONSENSUS_ALGORITHM_TYPES) {
                SelectionContainer {
                    Text(
                        text = algorithm.DESCRIPTION,
                        color = MyBrown,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            if (coinData?.HASHING_ALGORITHM_TYPES != null) {
                for (hash in coinData.HASHING_ALGORITHM_TYPES) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = hash.NAME,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(MyBrown)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}