package com.joincoded.bankapi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joincoded.bankapi.data.AmountChange
import com.joincoded.bankapi.data.User
import com.joincoded.bankapi.data.response.TokenResponse
import com.joincoded.bankapi.network.BankApiService
import com.joincoded.bankapi.network.RetrofitHelper
import kotlinx.coroutines.launch

class BankViewModel : ViewModel() {
    private val apiService = RetrofitHelper.getInstance().create(BankApiService::class.java)
    var token: TokenResponse? by mutableStateOf(null)
    var user: User? by mutableStateOf(null)


    fun signup(username: String, password: String, image: String = "") {
        viewModelScope.launch {
            try {

                val response = apiService.signup(User(username, password, null, image, null))
                token = response.body()
            } catch (e: Exception) {
                println("Error $e")
            }

        }
    }

    fun signin(username: String, password: String) {
        viewModelScope.launch {
            try {

                val response = apiService.signin(User(username, password, null, null, null))

                token = response.body()
            } catch (e: Exception) {
                println("Error $e")
            }

        }
    }

    fun deposit(amount: Double) {
        viewModelScope.launch {
            try {
                val response = apiService.deposit(token = token?.getBearerToken(),
                    AmountChange(amount))
                if (response.isSuccessful){
                    println("Deposit Successful")
                }else{
                    println("Deposit Failed")
                }
            } catch (e: Exception) {
                println("Error $e")
            }

        }
    }

    fun getAccount(){
        viewModelScope.launch {
            try {
                val response = apiService.getAccount(token = token?.getBearerToken())
                user = response.body()
            }   catch (e: Exception) {
                println("Error $e")
            }
        }
    }

    fun withdraw(amount: Double) {
        viewModelScope.launch {
            try {
                val response = apiService.withdraw(token = token?.getBearerToken(),
                    AmountChange(amount))
                if (response.isSuccessful){
                    println("Withdraw Successful")
                }else{
                    println("Withdraw Failed")
                }
            } catch (e: Exception) {
                println("Error $e")
            }

        }
    }

    fun transfer(username: String,amount: Double){
        viewModelScope.launch {
            try {

                val response = apiService.transfer(username,token = token?.getBearerToken(),AmountChange(amount))
                if (response.isSuccessful){

                    println("Successful transfer")
                }else{

                    println("Failed transfer")
                }
                }catch (e: Exception){
                    println("Error $e")
                }
        }
    }
}