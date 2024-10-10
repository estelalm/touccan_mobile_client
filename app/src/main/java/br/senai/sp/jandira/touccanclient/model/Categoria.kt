package br.senai.sp.jandira.touccanclient.model

import kotlinx.serialization.Serializable

@Serializable
data class Categoria(
    val id: Int = 0,
    val categoria: String = ""
)
