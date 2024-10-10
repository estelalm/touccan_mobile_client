package br.senai.sp.jandira.touccanclient.model

import kotlinx.serialization.Serializable

@Serializable
data class Dificuldade(
    val id: Int = 0,
    val dificuldade: String = ""
)
