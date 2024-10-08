package br.senai.sp.jandira.touccanclient.model

data class BicoPost(
    var titulo: String = "",
    var descricao: String = "",
    var horario_inicio: String = "",
    var data_inicio: String = "",
    var horario_limite: String = "",
    var data_limite: String = "",
    var salario: Number = 0,
    var id_dificuldade: Int = 0,
    var id_categoria: Int = 0,
    var id_cliente: Int = 0
)
