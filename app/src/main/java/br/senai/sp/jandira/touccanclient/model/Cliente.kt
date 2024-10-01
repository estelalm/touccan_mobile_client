package br.senai.sp.jandira.touccanclient.model

data class Cliente(
    val id: Int = 1,
    val nome_responsavel: String = "Estela Alves",
    val cpf_responsavel: String = "10298719812",
    val nome_fantasia: String = "Mercado Bom Lugar",
    val razao_social: String = "Mercado Bom Lugar LTD",
    val telefone: String = "11898982673",
    val cnpj: String = "12345678901234",
    val cep: String = "12298000",
    val senha: String = "Mercado@123",
    val premium: Int = 0,
    val foto: String = "",
    val email: String = "mercadoBlugar@gmail.com"
)
