package com.joincoded.bankapi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joincoded.bankapi.data.AmountChange
import com.joincoded.bankapi.data.Transaction
import com.joincoded.bankapi.data.User
import com.joincoded.bankapi.data.response.TokenResponse
import com.joincoded.bankapi.network.BankApiService
import com.joincoded.bankapi.network.RetrofitHelper
import kotlinx.coroutines.launch

class BankViewModel : ViewModel() {
    private val apiService = RetrofitHelper.getInstance().create(BankApiService::class.java)
    var token: TokenResponse? by mutableStateOf(null)
    var user: User? by mutableStateOf(null)
    var transactions: List<Transaction>? by mutableStateOf(null)

    fun signup(username: String, password: String, image: String = "", nav: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.signup(User(username, password, null, image, null))
                token = response.body()
            } catch (e: Exception) {
                println("Error $e")
            } finally {
                getAccount()
                nav()
            }

        }
    }

    fun signin(username: String, password: String, nav: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.signin(User(username, password, null, null, null))
                token = response.body()
            } catch (e: Exception) {
                println("Error $e")
            } finally {
                getAccount()
                nav()
            }

        }
    }

    fun deposit(amount: Double, nav: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.deposit(
                    token = token?.getBearerToken(),
                    AmountChange(amount)
                )
                if (response.isSuccessful) {
                    println("Deposit Successful")
                } else {
                    println("Deposit Failed")
                }
            } catch (e: Exception) {
                println("Error $e")
            } finally {
                getAccount()
                nav()
            }

        }
    }

    fun getAccount() {
        viewModelScope.launch {
            try {
                val response = apiService.getAccount(token = token?.getBearerToken())
                user = response.body()
            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }

    fun withdraw(amount: Double, nav: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.withdraw(
                    token = token?.getBearerToken(),
                    AmountChange(amount)
                )
                if (response.isSuccessful) {
                    println("Withdraw Successful")
                } else {
                    println("Withdraw Failed")
                }
            } catch (e: Exception) {
                println("Error $e")
            } finally {
                getAccount()
                nav()
            }

        }
    }

    fun transfer(username: String, amount: Double) {
        viewModelScope.launch {
            try {

                val response = apiService.transfer(
                    username,
                    token = token?.getBearerToken(),
                    AmountChange(amount)
                )
                if (response.isSuccessful) {

                    println("Successful transfer")
                } else {

                    println("Failed transfer")
                }
            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }
    fun updateAccount(username: String,password: String, nav: () -> Unit){
        viewModelScope.launch {
            try {
                val response = apiService.updateAccount(token = token?.getBearerToken(),
                    user = User(username, password, null, "", null))
            } catch (e: Exception){
                println("Error $e")
            } finally {
                getAccount()
                nav()
            }


        }
    }

    fun getTransactions(){
        viewModelScope.launch {
            try {
                val response = apiService.getTransactions(token = token?.getBearerToken())
                transactions = response.body()
            } catch (e:Exception){

                println("Error $e")
            }

        }
    }
}

