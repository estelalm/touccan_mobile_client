package br.senai.sp.jandira.touccanclient.model

data class BicoPost(
    val titulo: String = "",
    val descricao: String = "",
    val horario_inicio: String = "",
    val data_inicio: String = "",
    val horario_limite: String = "",
    val data_limite: String = "",
    val salario: Number = 0,
    val id_dificuldade: Int = 0,
    val id_categoria: Int = 0,
    val id_cliente: Int = 0
)
