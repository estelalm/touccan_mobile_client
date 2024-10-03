package br.senai.sp.jandira.touccanclient.service

import br.senai.sp.jandira.touccanclient.model.BicoPost
import br.senai.sp.jandira.touccanclient.model.Cliente
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BicoService {

    @Headers("Content-Type: application/json")
    @POST("bico")
    fun createBico(@Body cliente: BicoPost): Call<BicoPost>

}