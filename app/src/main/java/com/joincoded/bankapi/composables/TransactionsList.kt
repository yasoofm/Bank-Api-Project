package com.joincoded.bankapi.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joincoded.bankapi.R
import com.joincoded.bankapi.data.Transaction
import com.joincoded.bankapi.data.User


@Composable
fun TransactionsList(transactions: List<Transaction>?, user: User?){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(transactions ?: List(1) { Transaction(0, 0, 0.0, "") }){
            TransactionCard(transaction = it, user = user)
            Divider()
        }
    }
}

@Composable
fun TransactionCard(transaction: Transaction, user: User?){
    if (transaction.type.equals("withdraw")){
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Image(painter = painterResource(id = R.drawable.withdrawal), contentDescription = "withdraw", modifier = Modifier
                .padding(start = 20.dp)
                .size(50.dp), colorFilter = ColorFilter.tint(Color.Red))
            Text(text = transaction.amount.toString(), fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Spacer(modifier = Modifier.width(130.dp))
        }
    } else if (transaction.type.equals("deposit")){
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Image(painter = painterResource(id = R.drawable.deposit), contentDescription = "deposit", modifier = Modifier
                .padding(start = 20.dp)
                .size(50.dp), colorFilter = ColorFilter.tint(Color.Green))
            Text(text = transaction.amount.toString(), fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Spacer(modifier = Modifier.width(130.dp))
        }
    } else if (transaction.senderId == user?.id){
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Image(painter = painterResource(id = R.drawable.transfer), contentDescription = "transfer", modifier = Modifier
                .padding(start = 20.dp)
                .size(50.dp), colorFilter = ColorFilter.tint(Color.Red))
            Text(text = transaction.amount.toString(), fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Column (modifier = Modifier.padding(end = 20.dp).height(IntrinsicSize.Max)) {
                Text(text = "Sender ID: ${transaction.senderId}", fontSize = 13.sp)
                Text(text = "Receiver ID: ${transaction.receiverId}", fontSize = 13.sp)
            }
        }
    } else {
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Image(painter = painterResource(id = R.drawable.transfer), contentDescription = "transfer", modifier = Modifier
                .padding(start = 20.dp)
                .size(50.dp), colorFilter = ColorFilter.tint(Color.Green))
                Text(text = transaction.amount.toString(), fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Column (modifier = Modifier.padding(end = 20.dp).height(IntrinsicSize.Max)) {
                Text(text = "Sender ID: ${transaction.senderId}", fontSize = 13.sp)
                Text(text = "Receiver ID: ${transaction.receiverId}", fontSize = 13.sp)
            }
        }
    }
}

