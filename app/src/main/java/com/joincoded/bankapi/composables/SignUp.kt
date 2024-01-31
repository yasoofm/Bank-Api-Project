package com.joincoded.bankapi.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joincoded.bankapi.viewmodel.BankViewModel

@Composable
fun SignUpPage(bankViewMoedel : BankViewModel){
    Card(modifier = Modifier
        .fillMaxSize()) {
        var username by remember {mutableStateOf("")}
        var password by remember { mutableStateOf("")}

        TextField(value = username , onValueChange = {
            username = it
        })
        TextField(value = password , onValueChange = {
            password = it

        })

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                bankViewMoedel.signup(username,password,"")
            },
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        ) {
            Text("Sign up")
        }
    }
}
