package com.joincoded.bankapi.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joincoded.bankapi.viewmodel.BankViewModel

@Composable
fun AccountPage(bankViewModel: BankViewModel = viewModel()){
    Column {
        val user = bankViewModel.user
        Text(text = user?.username ?: "")
        Text(text = user?.password ?: "")
        Text(text = user?.balance?.toString() ?: "")
        Row {
            Button(onClick = { /*TODO*/ }) {
              Text(text = "Deposit")  
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Withdraw")
            }
        }
    }
}