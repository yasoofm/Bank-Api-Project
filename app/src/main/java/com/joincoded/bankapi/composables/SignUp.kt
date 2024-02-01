package com.joincoded.bankapi.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joincoded.bankapi.viewmodel.BankViewModel

@Composable
fun SignUpPage(bankViewModel : BankViewModel, toDetails: () -> Unit){
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var username by remember {mutableStateOf("")}
        var password by remember { mutableStateOf("")}

        Text(text = "Sign in", modifier = Modifier.align(Alignment.Start), fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = username, label = {Text(text = "Username")} , onValueChange = {
            username = it
        })
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = password, label = {Text(text = "Password")}, visualTransformation = PasswordVisualTransformation(), onValueChange = {
            password = it

        })

        Spacer(modifier = Modifier.height(80.dp))

        Row (horizontalArrangement = Arrangement.SpaceAround){
            Button(
                onClick = {
                    bankViewModel.signup(username,password,"", toDetails)
                },
                modifier = Modifier
                    .width(180.dp)
                    .padding(20.dp)
            ) {
                Text("Sign up")
            }
            Button(onClick = { bankViewModel.signin(username, password, toDetails) },
                modifier = Modifier
                    .width(180.dp)
                    .padding(20.dp)) {
                Text(text = "Sign in")
            }
        }
    }
}
