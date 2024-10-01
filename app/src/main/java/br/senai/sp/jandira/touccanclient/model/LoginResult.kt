package br.senai.sp.jandira.touccanclient.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResult(
    val cliente: ClientId
)

