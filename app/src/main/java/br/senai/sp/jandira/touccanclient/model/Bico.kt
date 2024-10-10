package br.senai.sp.jandira.touccanclient.model

import kotlinx.serialization.Serializable

@Serializable
data class Bico(
    val id :Int = 0,
    val titulo: String = "",
    val descricao: String = "",
    val horario_inicio: String = "",
    val data_inicio: String = "",
    val horario_limite: String = "",
    val data_limite: String = "",
    val salario: Int = 0,
    val finalizado: Int = 0,
    val categoria: List<Categoria> = listOf(),
    val dificuldade: List<Dificuldade> = listOf(),
    val id_cliente: Int = 0
)
