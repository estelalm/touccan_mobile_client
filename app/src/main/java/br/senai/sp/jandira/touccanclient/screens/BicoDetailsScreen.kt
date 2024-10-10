package br.senai.sp.jandira.touccanclient.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanclient.R
import br.senai.sp.jandira.touccanclient.ui.theme.Inter

//fun BicoDetails(navController: NavHostController, bicoClicado: Bico, mainActivity: MainActivity)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BicoDetails(navController: NavHostController) {

    val laranja = 0xffF07B07

    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEBEBEB)
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .height(100.dp)
                            .width(170.dp)) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp),
                            painter = painterResource(R.drawable.logo_touccan),
                            contentDescription = "Desenho de um, com o texto Touccan ao lado, a logo do aplicativo",
                        )
                    }

                },
                title = {
                },
                actions = {
                    Row (horizontalArrangement = Arrangement.End){
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.configuracoes),
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.carteira),
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.person),
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        } }
                }

            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = Color(0xFFEBEBEB)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {


                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.home),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.historico),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            painter = painterResource(R.drawable.notificacao),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }

                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.chat),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }

                    IconButton(
                        onClick = {
//                            navController.navigate("criarAnuncio/${clienteId}")
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.adicionar),
                            contentDescription = "Ícone laranja de adicionar anúncio",
                        )
                    }
                }

            }
        }
    ) { innerpadding ->

        Column (modifier = Modifier.padding(innerpadding), horizontalAlignment = Alignment.CenterHorizontally){
            Row (modifier = Modifier.fillMaxWidth()){
                IconButton(onClick = {
                    navController.popBackStack()
                }) {Icon(imageVector = Icons.Filled.ArrowBack, "Seta voltar", modifier = Modifier.height(24.dp)) }

            }
            Spacer(Modifier.height((100.dp)))
            Card() {
                Column (modifier = Modifier.padding(24.dp)) {
                    Column () {
                        Row(modifier = Modifier.padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically){
                            Card (shape = RoundedCornerShape(50.dp), modifier = Modifier.size(50.dp)) {
                                Image(painter = painterResource(R.drawable.blugar_logo), "",
                                    contentScale = ContentScale.FillWidth, alignment = Alignment.Center)
                            }
                            Text(
                                text = "Mercado Bom Lugar", fontWeight = FontWeight.Medium, fontSize = 20.sp, modifier = Modifier.padding(start = 12.dp)
                            )
                        }

                        Row {
                            Text(text = "Dificuldade: ", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                            Text(text = "Baixa ", color = Color(0xff378420), fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        }
                    }

                    Spacer(modifier=Modifier.height(50.dp))
                    Text("Assistente Admnistrativo", fontWeight = FontWeight.Medium, fontSize = 18.sp)
                    Text("Trabalho focado em organizar e atender clientes com intuito de disponibilidade hoje!", fontSize = 16.sp)
                    Spacer(modifier=Modifier.height(50.dp))
                    Column {
                        Text(
                            text = "Data: 24/10/2024",
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "Início: 08:30", fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "Término: 17:30", fontWeight = FontWeight.Black
                        )
                        Row {
                            Text(
                                text = "Pagamento: ", fontWeight = FontWeight.Black
                            )
                            Text(
                                text = "R$200,00", fontWeight = FontWeight.Black, color = Color(0xff378420)
                            )
                        }
                    }
                }
            }
            Spacer(modifier=Modifier.height(50.dp))
            Button(
                modifier = Modifier.width(250.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(laranja)
                ),
                onClick = {}
            ) {
                Text("Ver candidatos",
                fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)
            }
            Button(
                modifier = Modifier.width(200.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                onClick = {}
            ) {Text("Excluir anúncio",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold) }
        }



    }

}