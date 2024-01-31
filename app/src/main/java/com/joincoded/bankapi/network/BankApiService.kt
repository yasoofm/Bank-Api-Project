package com.joincoded.bankapi.network

import com.joincoded.bankapi.data.AmountChange
import com.joincoded.bankapi.data.User
import com.joincoded.bankapi.data.response.TokenResponse
import com.joincoded.bankapi.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BankApiService {

    @POST(Constants.signupEndpoint)
    suspend fun signup(@Body user: User): Response<TokenResponse>

    @POST(Constants.signinEndpoint)
    suspend fun signin(@Body user: User): Response<TokenResponse>

    @PUT(Constants.depositEndpoint)
    suspend fun deposit(
        @Header(Constants.authorization) token: String?,
        @Body amountChange: AmountChange
    ): Response<Unit>

    @PUT(Constants.withdrawEndpoint)
    suspend fun withdraw(
        @Header(Constants.authorization) token: String?,
        @Body amountChange: AmountChange
    ): Response<Unit>

    @PUT("${Constants.transferEndpoint}/{userName}")
    suspend fun transfer(@Path("UserName") userName: String,
                         @Header(Constants.authorization) token: String?,
                         @Body amountChange: AmountChange): Response<Unit>


    @GET(Constants.accountEndpoint)
    suspend fun getAccount(@Header(Constants.authorization) token: String?): Response<User>

    @PUT(Constants.updateEndpoints)
    suspend fun updateAccount(@Header(Constants.authorization) token: String?,
                              @Body user: User):Response<Unit>
}