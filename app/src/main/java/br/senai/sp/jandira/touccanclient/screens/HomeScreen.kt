package br.senai.sp.jandira.touccanclient.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import br.senai.sp.jandira.touccanclient.service.RetrofitFactory
import br.senai.sp.jandira.touccanclient.ui.theme.Inter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, idCliente: ClientId, mainActivity: MainActivity) {

    val clienteId = Json.encodeToString(idCliente)

//    var bicosList = remember {
//        mutableStateOf(listOf<Bico>())
//    }
//
//    val callBicoList = RetrofitFactory()
//        .getBicoService()
//        .getAllBicos()
//
//    callBicoList.enqueue(object: Callback<ResultBico> {
//        override fun onResponse(call: Call<ResultBico>, res: Response<ResultBico>) {
//            Log.i("Response: ", res.toString())
//            bicosList.value = res.body()!!.bicos
//        }
//
//        override fun onFailure(call: Call<ResultBico>, t: Throwable) {
//            Log.i("Falhou:", t.toString())
//        }
//    })


    val laranja = 0xffF07B07

    val cinza = 0xffC6C5C5

    var meusAnunciosState = remember{
        mutableStateOf(false)
    }

    var pendentesState = remember{
        mutableStateOf(true)
    }

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
                            navController.navigate("criarAnuncio/${clienteId}")
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
        Column (Modifier.padding(vertical = 100.dp)){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceAround

            ){
                Button(
                    onClick = {
                        pendentesState.value = false
                        meusAnunciosState.value = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {

                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "Meus Anúncios",
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontStyle = if(meusAnunciosState.value) {
                                FontStyle.Italic}
                            else {
                                FontStyle.Normal},
                            fontWeight = FontWeight.SemiBold,
                            color = if(meusAnunciosState.value) {
                                Color.Black }
                            else {
                                Color(cinza)
                            }
                        )
                        if(meusAnunciosState.value){
                            Box(
                                modifier = Modifier
                                    .background(Color(laranja))
                                    .height(1.dp)
                                    .width(100.dp)
                            )
                        }
                    }

                }
                Button(onClick = {
                    pendentesState.value = true
                    meusAnunciosState.value = false
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "Trabalhos Pendentes",
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontStyle = if(pendentesState.value) {
                                FontStyle.Italic}
                            else {
                                FontStyle.Normal},
                            fontWeight = FontWeight.SemiBold,
                            color = if(pendentesState.value) {
                                Color.Black }
                            else {
                                Color(cinza)
                            }
                        )
                        if(pendentesState.value){
                            Box(
                                modifier = Modifier
                                    .background(Color(laranja))
                                    .height(1.dp)
                                    .width(100.dp)

                            )
                        }
                    }
                }
            }

        }

    }

}
