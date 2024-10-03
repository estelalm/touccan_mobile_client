package br.senai.sp.jandira.touccanclient.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanclient.MainActivity
import br.senai.sp.jandira.touccanclient.R
import br.senai.sp.jandira.touccanclient.model.ClientId
import br.senai.sp.jandira.touccanclient.model.LoginResult
import br.senai.sp.jandira.touccanclient.service.RetrofitFactory
import br.senai.sp.jandira.touccanclient.ui.theme.Inter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAnuncio(navController: NavHostController, idCliente: ClientId, mainActivity: MainActivity
) {

    val laranja = 0xffF07B07

    val cinza = 0xffC6C5C5

    var tituloState = remember {
        mutableStateOf("")
    }

    var descricaoState = remember {
        mutableStateOf("")
    }

    var horarioState = remember {
        mutableStateOf("")
    }


    var dataState = remember {
        mutableStateOf("")
    }


    var localizacaoState = remember {
        mutableStateOf("")
    }


    var salarioState = remember {
        mutableStateOf("")
    }


    var dificuldadeState = remember {
        mutableStateOf("")
    }


    var categoriaState = remember {
        mutableStateOf("")
    }


    var dataPrazoState = remember {
        mutableStateOf("")
    }

    var horarioPrazoState = remember {
        mutableStateOf("")
    }

    var caracteresState = remember{
        mutableStateOf(0)
    }

//    Scaffold (
//        modifier = Modifier.fillMaxSize(),
//        containerColor = Color(0xFFEBEBEB),
//        topBar = {
//            TopAppBar(
//                modifier =  Modifier.height(100.dp),
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFFEBEBEB)
//                ),
//                navigationIcon = {
//                    IconButton(
//                        onClick = {},
//                        modifier = Modifier
//                            .size(140.dp)
//                    ) {
//                        Icon(
//                            modifier = Modifier
//
//                                .padding(horizontal = 10.dp),
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Seta para trás: voltar",
//                        )
//                    }
//                },
//                title = {
//                },
//                actions = {
//                    IconButton(modifier = Modifier.fillMaxHeight(),onClick = {}) {
//                        Icon(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(horizontal = 10.dp),
//                            painter = painterResource(R.drawable.logo_touccan),
//                            contentDescription = "Desenho de um, com o texto Touccan ao lado, a logo do aplicativo",
//                        )
//                    }
//
//                }
//
//            )
//        }
//    )

    Column (
        modifier = Modifier.fillMaxSize().padding(top = 32.dp).verticalScroll(rememberScrollState()),
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().height(80.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(
                onClick = {},
            ) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Seta para trás: voltar",
                )
            }

            IconButton(onClick = {}, modifier = Modifier.height(200.dp)) {
                Icon(
                    modifier = Modifier.height(200.dp),
                    painter = painterResource(R.drawable.logo_touccan),
                    contentDescription = "Desenho de um, com o texto Touccan ao lado, a logo do aplicativo",
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp, vertical = 10.dp)
        ){
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    text = "Criar Anúncio",
                    textAlign = TextAlign.Center,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
                Box(
                    modifier = Modifier
                        .background(Color(laranja))
                        .height(1.dp)
                        .width(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Título do cargo",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = tituloState.value,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color(laranja),
                    focusedIndicatorColor = Color(laranja)
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = {tituloState.value = it}
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Descrição",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            TextField(
                modifier = Modifier.fillMaxWidth().height(180.dp),
                value = descricaoState.value,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color(laranja),
                    focusedIndicatorColor = Color(laranja)
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = {
                    if(it.length <=500)
                        descricaoState.value = it

                    caracteresState.value = descricaoState.value.length
                                },
                suffix = {Text("${caracteresState.value}/500")}
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Text(
                        text = "Horário",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TextField(
                        modifier = Modifier.width(165.dp),
                        value = tituloState.value,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedIndicatorColor = Color(laranja),
                            focusedIndicatorColor = Color(laranja)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onValueChange = {tituloState.value = it}
                    )
                }
                Column {
                    Text(
                        text = "Data",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TextField(
                        modifier = Modifier.width(165.dp),
                        value = tituloState.value,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedIndicatorColor = Color(laranja),
                            focusedIndicatorColor = Color(laranja)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onValueChange = {tituloState.value = it}
                    )
                }
            }

            Text(
                text = "Localização",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = tituloState.value,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color(laranja),
                    focusedIndicatorColor = Color(laranja)
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = {tituloState.value = it}
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Salário",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = tituloState.value,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color(laranja),
                    focusedIndicatorColor = Color(laranja)
                ),
                shape = RoundedCornerShape(10.dp),
                prefix = { Text("R$", fontFamily = Inter) },
                onValueChange = {tituloState.value = it}
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Nível de dificuldade",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = tituloState.value,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color(laranja),
                    focusedIndicatorColor = Color(laranja)
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = {tituloState.value = it}
            )
            Spacer(modifier = Modifier.height(12.dp))

//            DropdownMenu(
//                expanded = true,
//                onDismissRequest = {}
//            ) {
//                DropdownMenuItem(
//                    {Text("Item 1")},
//                    onClick = {}
//                )
//                DropdownMenuItem(
//                    {Text("Item 2")},
//                    onClick = {}
//                )
//            }

            Text(
                text = "Categoria",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = tituloState.value,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color(laranja),
                    focusedIndicatorColor = Color(laranja)
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = {tituloState.value = it}
            )
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Prazo do anúncio",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                    TextField(
                        modifier = Modifier.width(165.dp),
                        value = tituloState.value,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedIndicatorColor = Color(laranja),
                            focusedIndicatorColor = Color(laranja)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        label = {
                            Text(
                                "Data",
                                color = Color(cinza),
                                fontFamily = Inter
                            )
                                },
                        onValueChange = {tituloState.value = it}
                    )
                    TextField(
                        modifier = Modifier.width(165.dp),
                        value = tituloState.value,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedIndicatorColor = Color(laranja),
                            focusedIndicatorColor = Color(laranja)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        label = {
                            Text(
                                "Horário",
                                color = Color(cinza),
                                fontFamily = Inter
                            )
                                },
                        onValueChange = {tituloState.value = it}
                    )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {


                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(46.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(laranja)
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 5.dp
                ),
            ) {
                Text(
                    text = "CRIAR",
                    color = Color.White,
                    fontFamily = Inter,
                    fontSize = 18.sp,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

        }

    }



}