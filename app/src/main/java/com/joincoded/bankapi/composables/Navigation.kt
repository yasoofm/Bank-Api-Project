package com.joincoded.bankapi.composables

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.joincoded.bankapi.viewmodel.BankViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joincoded.bankapi.utils.Routes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppContent() {
    val bankViewModel: BankViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.signupRoute) {
        composable(Routes.signupRoute){
            SignUpPage(bankViewModel = bankViewModel, toDetails = {navController.navigate(Routes.accountRoute)})
        }
        composable(Routes.accountRoute){
            AccountPage(bankViewModel = bankViewModel,
                toDeposit = {navController.navigate(Routes.depositRoute)},
                toWithdraw = {navController.navigate(Routes.withdrawRoute)},
                toUpdate = {navController.navigate(Routes.updateRoute)},
                toTransfer = {navController.navigate(Routes.transferRoute)},
                logOut = {
                    bankViewModel.token = null
                    navController.navigate(Routes.signupRoute)})
        }
        composable(Routes.depositRoute){
            DepositComposable(bankViewModel = bankViewModel, toDetails = {navController.navigate(Routes.accountRoute)})
        }
        composable(Routes.withdrawRoute){
            WithdrawComposable(bankViewModel = bankViewModel, toDetails = {navController.navigate(Routes.accountRoute)})
        }
        composable(Routes.updateRoute){
            UpdatePage(bankViewModel = bankViewModel, toDetails = {navController.navigate(Routes.accountRoute)})
        }
        composable(Routes.transferRoute){
            TransferPage(bankViewModel = bankViewModel, toDetails = {navController.navigate(Routes.accountRoute)})
        }
    }
}
