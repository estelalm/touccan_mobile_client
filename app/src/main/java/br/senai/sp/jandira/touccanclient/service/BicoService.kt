package br.senai.sp.jandira.touccanclient.service

import br.senai.sp.jandira.touccanclient.model.BicoPost
import br.senai.sp.jandira.touccanclient.model.Categorias
import br.senai.sp.jandira.touccanclient.model.Cliente
import br.senai.sp.jandira.touccanclient.model.Dificuldades
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface BicoService {

    @Headers("Content-Type: application/json")
    @POST("bico")
    fun createBico(@Body cliente: BicoPost): Call<BicoPost>


    @GET("categoria")
    fun getAllCategorias(): Call<Categorias>

    @GET("dificuldade")
    fun getAllDificuldades(): Call<Dificuldades>

}