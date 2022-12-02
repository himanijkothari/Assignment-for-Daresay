package com.example.assignment_for_daresay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun MovieApp() {
    Scaffold(
        topBar = {
            TopAppBar() {
                Column {
                    AppBar()
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
    ) {}
}


@Composable
fun AppBar() {
   Row(){
       Text(
           text= stringResource(id = R.string.label_movie_app)
       )

       Button(onClick = { /*TODO*/ }) {
            Text(
                text= stringResource(id = R.string.label_sign_in)
            )
       }

   }
}
