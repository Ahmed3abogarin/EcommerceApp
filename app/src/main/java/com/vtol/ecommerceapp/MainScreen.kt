package com.vtol.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vtol.ecommerceapp.components.BuyButton
import com.vtol.ecommerceapp.ui.theme.EcommerceAppTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier.background(Color.White)
            .fillMaxSize()
            .fillMaxWidth()
            .statusBarsPadding()
            .navigationBarsPadding()

    ) {
        Column(
            modifier = modifier.verticalScroll(rememberScrollState())

        ) {


            // Top App
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier
                            .size(38.dp)
                            .padding(7.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }


                Card(
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier
                            .size(38.dp)
                            .padding(7.dp),
                        painter = painterResource(R.drawable.ic_favorite),
                        tint = Color(0XFF48B448) ,
                        contentDescription = ""
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))



            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 16f),
                contentDescription = "",
                painter = painterResource(R.drawable.xbox_controller)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        fontSize = 28.sp,
                        text = "Thrustmaster",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        fontSize = 28.sp,
                        text = "Controller",
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(
                        fontSize = 15.sp,
                        text = "Price Starts"
                    )
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 26.sp,
                        text = "$49.99"
                    )
                }

            }


            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 18.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color(0XFF107C10),
                text = "Innovation Gaming Controller "
            )



                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 6.dp),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    text = "Korem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque"
                )





        }

        val gradientColor = listOf(Color(0XFFF5FFF8), Color.Transparent).asReversed()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(brush = Brush.verticalGradient(gradientColor))
                .align(Alignment.BottomCenter)
        )

        BuyButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .padding(bottom = 6.dp)
        )
    }
}


@Preview
@Composable
fun MainPreview() {
    EcommerceAppTheme {
        MainScreen()
    }
}