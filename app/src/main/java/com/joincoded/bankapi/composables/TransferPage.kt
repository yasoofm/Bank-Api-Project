package com.joincoded.bankapi.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joincoded.bankapi.viewmodel.BankViewModel

@Composable
fun TransferPage(bankViewModel: BankViewModel = viewModel(), toDetails: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var amount by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Transfer",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Column {
            InputField(
                value = username,
                onValueChange = { username = it },
                label = "Username"
            )
            InputField(
                value = amount,
                onValueChange = { amount = it },
                label = "Amount"
            )

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    bankViewModel.transfer(username = username, amount = amount.toDouble(), toDetails)
                },
                modifier = Modifier.width(300.dp).align(Alignment.CenterHorizontally)
            ) {
                Text("Transfer")
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}
