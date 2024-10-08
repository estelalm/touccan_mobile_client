package br.senai.sp.jandira.touccanclient.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "https://touccan-backend-8a78.onrender.com/2.0/touccan/"
//    private val BASE_URL = "https://touccan-hdazhteka2hxh3fc.brazilsouth-01.azurewebsites.net/2.0/touccan/"


    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getClienteService(): ClienteService {
        return retrofitFactory.create(ClienteService::class.java)
    }

    fun getBicoService(): BicoService {
        return retrofitFactory.create(BicoService::class.java)
    }

}