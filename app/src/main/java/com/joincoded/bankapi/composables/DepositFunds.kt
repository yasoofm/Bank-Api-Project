package com.joincoded.bankapi.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joincoded.bankapi.viewmodel.BankViewModel

@Composable
fun DepositComposable(bankViewModel: BankViewModel = viewModel()) {
    var amount by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        // horizontalAlignment = LineHeightStyle.Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter deposit amount",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        InputField(
            value = amount,
            onValueChange = { amount = it.toString() },
            label = "Amount"
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                bankViewModel.deposit(amount.toDouble())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Withdraw")
        }
    }
}

