package com.joincoded.bankapi.viewmodel

import android.content.Context
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
import com.joincoded.bankapi.utils.Routes
import kotlinx.coroutines.launch

class BankViewModel : ViewModel() {
    private val apiService = RetrofitHelper.getInstance().create(BankApiService::class.java)
    var myToken: TokenResponse? by mutableStateOf(null)
    var user: User? by mutableStateOf(null)
    var transactions: List<Transaction>? by mutableStateOf(null)
    var context: Context? = null



    fun signup(username: String, password: String, image: String = "", nav: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.signup(User(username, password, null, image, null, null))
                myToken = response.body()
            } catch (e: Exception) {
                println("Error $e")
            } finally {
                if (myToken != null) {
                    saveToken()
                    getAccount()
                    getTransactions()
                    nav()
                }
            }

        }
    }

    fun signin(username: String, password: String, nav: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.signin(User(username, password, null, null, null, null))
                myToken = response.body()
            } catch (e: Exception) {
                println("Error $e")
            } finally {
                if (myToken != null) {
                    saveToken()
                    getAccount()
                    getTransactions()
                    nav()
                }
            }

        }
    }

    fun deposit(amount: Double, nav: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.deposit(
                    token = myToken?.getBearerToken(),
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
                getTransactions()
                nav()
            }

        }
    }

    fun getAccount() {
        viewModelScope.launch {
            try {
                val response = apiService.getAccount(token = myToken?.getBearerToken())
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
                    token = myToken?.getBearerToken(),
                    AmountChange(amount)
                )
            } catch (e: Exception) {
                println("Error $e")
            } finally {
                getAccount()
                getTransactions()
                nav()
            }

        }
    }

    fun transfer(username: String, amount: Double, nav: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.transfer(
                    userName = username,
                    token = myToken?.getBearerToken(),
                    amountChange = AmountChange(amount)
                )
                if (response.isSuccessful) {

                    println("Successful transfer")
                } else {

                    println("Failed transfer")
                }
            } catch (e: Exception) {
                println("Error $e")
            } finally {
                getAccount()
                getTransactions()
                nav()
            }
        }
    }

    fun updateAccount(username: String, password: String, nav: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.updateAccount(
                    token = myToken?.getBearerToken(),
                    user = User(username, password, null, "", null, null)
                )
            } catch (e: Exception) {
                println("Error $e")
            } finally {
                getAccount()
                getTransactions()
                nav()
            }


        }
    }

    fun getTransactions() {
        viewModelScope.launch {
            try {
                val response = apiService.getTransactions(token = myToken?.getBearerToken())
                transactions = response.body()
            } catch (e: Exception) {
                println("Error $e")
            }

        }
    }

    fun saveToken() {
        val sharedPref = context?.getSharedPreferences("tokenFile", Context.MODE_PRIVATE)
        sharedPref?.edit()?.putString("MY_TOKEN", myToken.toString())?.apply()
    }

    fun getToken() {
        val sharedPref = context?.getSharedPreferences("tokenFile", Context.MODE_PRIVATE)
//        myToken = TokenResponse(token = sharedPref?.getString("MY_TOKEN", null))
        var savedToken = sharedPref?.getString("MY_TOKEN", null)
        if (savedToken != null)
            myToken = TokenResponse(token = savedToken)

        println("TOKEN ${savedToken}")
    }

}

