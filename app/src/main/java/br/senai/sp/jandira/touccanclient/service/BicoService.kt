package br.senai.sp.jandira.touccanclient.service

import br.senai.sp.jandira.touccanclient.model.BicoPost
import br.senai.sp.jandira.touccanclient.model.Categorias
import br.senai.sp.jandira.touccanclient.model.ClienteIdPost
import br.senai.sp.jandira.touccanclient.model.Dificuldades
import br.senai.sp.jandira.touccanclient.model.GetBicoResult
import br.senai.sp.jandira.touccanclient.model.PostBicoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface BicoService {

    @Headers("Content-Type: application/json")
    @POST("bicos")
    fun createBico(@Body bico: BicoPost): Call<PostBicoResponse>

    @Headers("Content-Type: application/json")
    @POST("bico")
    fun getBicoByClient(@Body cliente: ClienteIdPost): Call<GetBicoResult>

    @GET("categoria")
    fun getAllCategorias(): Call<Categorias>

    @GET("dificuldade")
    fun getAllDificuldades(): Call<Dificuldades>

}