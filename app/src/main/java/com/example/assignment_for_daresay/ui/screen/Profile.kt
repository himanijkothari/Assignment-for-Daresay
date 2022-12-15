package com.example.assignment_for_daresay.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.assignment_for_daresay.ui.theme.gradientRadial

@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .background(gradientRadial)
            .fillMaxSize()
    ){
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://assets1.lottiefiles.com/packages/lf20_oom3wF.json"))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }
}