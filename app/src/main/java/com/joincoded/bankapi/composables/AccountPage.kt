package com.joincoded.bankapi.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joincoded.bankapi.viewmodel.BankViewModel

@Composable
fun AccountPage(bankViewModel: BankViewModel = viewModel(), toDeposit: () -> Unit, toWithdraw: () -> Unit, toUpdate: () -> Unit, toTransfer: () -> Unit, logOut: () -> Unit){
    Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
        val user = bankViewModel.user
        Text(text = user?.username ?: "")
        Text(text = user?.password ?: "")
        Text(text = user?.balance?.toString() ?: "")
        Row {
            Button(onClick = { toDeposit() }) {
              Text(text = "Deposit")  
            }
            Button(onClick = { toWithdraw() }) {
                Text(text = "Withdraw")
            }
            Button(onClick = { toTransfer() }) {
                Text(text = "Transfer")
            }
        }
        Row {
            Button(onClick = { toUpdate() }) {
                Text(text = "Update profile")
            }
            Button(onClick = { logOut() }) {
                Text(text = "Log out")
            }
        }

        TransactionsList(transactions = bankViewModel.transactions, user = bankViewModel.user)
    }
}