package com.joincoded.bankapi.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joincoded.bankapi.R
import com.joincoded.bankapi.viewmodel.BankViewModel

@Composable
fun AccountPage(bankViewModel: BankViewModel = viewModel(), toDeposit: () -> Unit, toWithdraw: () -> Unit, toUpdate: () -> Unit, toTransfer: () -> Unit, logOut: () -> Unit){
    Scaffold (bottomBar = { Row (modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .padding(5.dp)
        .clip(RoundedCornerShape(18.dp))
        .background(Color(0xFFA00EB8)), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically){
        IconButton(onClick = { toDeposit() }) {
            Icon(painter = painterResource(id = R.drawable.deposit), contentDescription = "", tint = Color.White)
        }
        IconButton(onClick = { toWithdraw() }) {
            Icon(painter = painterResource(id = R.drawable.withdrawal), contentDescription = "", tint = Color.White)
        }
        IconButton(onClick = { toTransfer() }) {
            Icon(painter = painterResource(id = R.drawable.transfer), contentDescription = "", tint = Color.White)
        }
        IconButton(onClick = { toUpdate() }) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "", tint = Color.White)
        }
    }}){
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally){
            val user = bankViewModel.user
            Card (modifier = Modifier
                .width(390.dp)
                .height(180.dp)
                .padding(10.dp)
                ){
                Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally){

                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(text = user?.username ?: "", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                        IconButton(onClick = { logOut() },) {
                            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Logout")
                        }
                    }

                    Text(text = "${user?.balance?.toString() ?: ""} KWD", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                }
            }

            TransactionsList(transactions = bankViewModel.transactions, user = bankViewModel.user)
        }
    }
}