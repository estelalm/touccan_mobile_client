package br.senai.sp.jandira.touccanclient.service

import br.senai.sp.jandira.touccanclient.model.Cliente
import br.senai.sp.jandira.touccanclient.model.Login
import br.senai.sp.jandira.touccanclient.model.LoginResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ClienteService {

    @Headers("Content-Type: application/json")
    @POST("cliente")
    fun saveClient(@Body cliente: Cliente): Call<Cliente>

    @Headers("Content-Type: application/json")
    @POST("login/cliente")
    fun loginCLient(@Body cliente: Login): Call<LoginResult>

    @GET("cliente/{id}")
    fun getUserById(@Path("id") id: Int): Call<Cliente>

}