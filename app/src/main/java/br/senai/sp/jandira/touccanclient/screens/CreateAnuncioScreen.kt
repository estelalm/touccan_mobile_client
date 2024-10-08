package br.senai.sp.jandira.touccanclient.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanclient.MainActivity
import br.senai.sp.jandira.touccanclient.R
import br.senai.sp.jandira.touccanclient.model.BicoPost
import br.senai.sp.jandira.touccanclient.model.Categoria
import br.senai.sp.jandira.touccanclient.model.Categorias
import br.senai.sp.jandira.touccanclient.model.ClientId
import br.senai.sp.jandira.touccanclient.model.Dificuldade
import br.senai.sp.jandira.touccanclient.model.Dificuldades
import br.senai.sp.jandira.touccanclient.model.LoginResult
import br.senai.sp.jandira.touccanclient.model.PostBicoResponse
import br.senai.sp.jandira.touccanclient.service.RetrofitFactory
import br.senai.sp.jandira.touccanclient.ui.theme.Inter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAnuncio(navController: NavHostController, idCliente: ClientId, mainActivity: MainActivity
) {

    val laranja = 0xffF07B07
    val cinza = 0xffC6C5C5

    var novoBico = BicoPost()

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

    var caracteresState = remember {
        mutableStateOf(0)
    }
    val isLoadingCategoria = remember { mutableStateOf(true) }
    val isLoadingDificuldade = remember { mutableStateOf(true) }


    //datas
    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
    }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""


    var showDatePrazoPicker by remember { mutableStateOf(false) }
    val datePrazoPickerState = rememberDatePickerState()
    val selectedDatePrazo = datePrazoPickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

//    fun validarData (data: String): String {
//
//        if(data != "") return data.split('/').reversed().joinToString("-")
//
//    }


    //horarios
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTime = "${timePickerState.hour}:" + timePickerState.minute

    // CATEGORIAS
    var categoriaList = remember { mutableStateOf(listOf<Categoria>()) }
    val callCategoriaList = RetrofitFactory()
        .getBicoService()
        .getAllCategorias()

    callCategoriaList.enqueue(object : Callback<Categorias> {
        override fun onResponse(call: Call<Categorias>, res: Response<Categorias>) {
            Log.i("ResponseC: ", res.toString())
            categoriaList.value = res.body()!!.categorias
            isLoadingCategoria.value = false
            Log.i("ResponseC: ", categoriaList.value.toString())
        }

        override fun onFailure(call: Call<Categorias>, t: Throwable) {
            Log.i("Falhou:", t.toString())
        }
    })

    // DIFICULDADE
    var dificuldadeList = remember { mutableStateOf(listOf<Dificuldade>()) }
    val callDificuldadeList = RetrofitFactory()
        .getBicoService()
        .getAllDificuldades()

    callDificuldadeList.enqueue(object : Callback<Dificuldades> {
        override fun onResponse(call: Call<Dificuldades>, res: Response<Dificuldades>) {
            Log.i("ResponseD: ", res.toString())
            dificuldadeList.value = res.body()!!.dificuldade
            isLoadingDificuldade.value = false

        }

        override fun onFailure(call: Call<Dificuldades>, t: Throwable) {
            Log.i("Falhou:", t.toString())
        }
    })

    var selectedIndexCategoria by rememberSaveable { mutableStateOf(0) }
    var selectedIndexDificuldade by rememberSaveable { mutableStateOf(0) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
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
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                onValueChange = { tituloState.value = it }
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Descrição",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                value = descricaoState.value,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color(laranja),
                    focusedIndicatorColor = Color(laranja)
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = {
                    if (it.length <= 500)
                        descricaoState.value = it

                    caracteresState.value = descricaoState.value.length
                },
                suffix = { Text("${caracteresState.value}/500") }
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Horário",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TextField(
                        modifier = Modifier.width(165.dp),
                        value = horarioState.value,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedIndicatorColor = Color(laranja),
                            focusedIndicatorColor = Color(laranja)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onValueChange = { horarioState.value = it },
                        trailingIcon = {
                            IconButton(onClick = { showTimePicker = !showTimePicker }) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Selecione o horário"
                                )
                            }
                        }
                    )
                }
                if (showTimePicker) {
                    Popup(
                        onDismissRequest = { showTimePicker = false },
                        alignment = Alignment.TopStart
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = 64.dp)
                                .shadow(elevation = 4.dp)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(16.dp)
                        ) {
                            TimeInput(
                                    state = timePickerState
                            )
                        }
                    }
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
                        value = selectedDate,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedIndicatorColor = Color(laranja),
                            focusedIndicatorColor = Color(laranja)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onValueChange = { dataState.value = it },
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker = !showDatePicker }) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Select date"
                                )
                            }
                        }
                    )
                }
            }

            if (showDatePicker) {
                Popup(
                    onDismissRequest = { showDatePicker = false },
                    alignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 64.dp)
                            .shadow(elevation = 4.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                    }
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
                    value = localizacaoState.value,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color(laranja),
                        focusedIndicatorColor = Color(laranja)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { localizacaoState.value = it }
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
                    value = salarioState.value,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color(laranja),
                        focusedIndicatorColor = Color(laranja)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    prefix = { Text("R$", fontFamily = Inter) },
                    onValueChange = { salarioState.value = it }
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Nível de dificuldade",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                if (isLoadingDificuldade.value) {
                    CircularProgressIndicator()
                } else {
                    Log.i("Dificuldades", dificuldadeList.value.toString())
                    DropdownDificuldade(
                        itemList = dificuldadeList.value,
                        selectedIndex = selectedIndexDificuldade,
                        onItemClick = { selectedIndexDificuldade = it })
                }
                Spacer(modifier = Modifier.height(12.dp))


                Text(
                    text = "Categoria",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                if (isLoadingCategoria.value) {
                    CircularProgressIndicator() // Ou outro componente de loading
                } else {
                    // Depois que a lista estiver carregada, chama o DropdownCategoria
                    Log.i("Categorias", categoriaList.value.toString())
                    DropdownCategoria(
                        itemList = categoriaList.value,
                        selectedIndex = selectedIndexCategoria,
                        onItemClick = { selectedIndexCategoria = it })
                }



                Spacer(modifier = Modifier.height(12.dp))


                Text(
                    text = "Prazo do anúncio",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        modifier = Modifier.width(165.dp),
                        value = selectedDatePrazo,
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
                        onValueChange = { dataPrazoState.value = it },
                        trailingIcon = {
                            IconButton(onClick = { showDatePrazoPicker = !showDatePrazoPicker }) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Select date"
                                )
                            }
                        }
                    )
                    if (showDatePrazoPicker) {
                        Popup(
                            onDismissRequest = { showDatePrazoPicker = false },
                            alignment = Alignment.TopStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = 64.dp)
                                    .shadow(elevation = 4.dp)
                                    .background(MaterialTheme.colorScheme.surface)
                                    .padding(16.dp)
                            ) {
                                DatePicker(
                                    state = datePrazoPickerState,
                                    showModeToggle = false
                                )
                            }
                        }
                    }
                    TextField(
                        modifier = Modifier.width(165.dp),
                        value = horarioPrazoState.value,
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
                        onValueChange = { horarioPrazoState.value = it }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {

                        novoBico.titulo = tituloState.value
                        novoBico.descricao = descricaoState.value
                        novoBico.horario_inicio = horarioState.value
                        novoBico.horario_limite = horarioPrazoState.value
                        novoBico.data_inicio = dataState.value
                        novoBico.data_limite = dataPrazoState.value
                        novoBico.salario = salarioState.value.toFloat()
                        novoBico.id_cliente = idCliente.id
                        novoBico.id_categoria = categoriaList.value[selectedIndexCategoria].id
                        novoBico.id_dificuldade = dificuldadeList.value[selectedIndexDificuldade].id

                        val sendBico = RetrofitFactory()
                            .getBicoService()
                            .createBico(novoBico)

                        sendBico.enqueue(object : Callback<PostBicoResponse> {
                            override fun onResponse(
                                call: Call<PostBicoResponse>,
                                res: Response<PostBicoResponse>
                            ) {
                                Log.i("Response Post: ", res.toString())
                                Log.i("Bico: ", novoBico.toString())
                                Log.i("Response Post: ", res.body()!!.toString())
                            }

                            override fun onFailure(call: Call<PostBicoResponse>, t: Throwable) {
                                Log.i("Falhou:", t.toString())
                            }
                        })

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




@Composable
fun DropdownCategoria(
    itemList: List<Categoria>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {

    var showDropdown by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val laranja = 0xffF07B07

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // button
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(50.dp)
                .padding(4.dp)
                .clickable { showDropdown = !showDropdown },
//            .clickable { showDropdown = !showDropdown },
            contentAlignment = Alignment.Center
        ) {
            Log.i("Lista", itemList.toString())
            Text(text = itemList[selectedIndex].categoria, modifier = Modifier.padding(3.dp))
        }

        // dropdown list
        Box(
            modifier = Modifier.border(
                width = 1.dp,
                color = Color(laranja),
                shape = RoundedCornerShape(10.dp)
            ),
        ) {
            if (showDropdown) {
                Popup(
                    alignment = Alignment.TopCenter,
                    properties = PopupProperties(
                        excludeFromSystemGesture = true,
                    ),
                    // to dismiss on click outside
                    onDismissRequest = { showDropdown = false }
                ) {

                    Column(
                        modifier = Modifier
                            .height(90.dp)
                            .verticalScroll(state = scrollState)
                            .width(350.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        itemList.onEachIndexed { index, item ->
                            if (index != 0) {
                                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.White)
                                    .width(350.dp)
                                    .clickable {
                                        onItemClick(index)
                                        showDropdown = !showDropdown
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = item.categoria, fontFamily = Inter)
                            }
                        }

                    }
                }
            }
        }
    }

}

@Composable
fun DropdownDificuldade(
    itemList: List<Dificuldade>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {

    var showDropdown by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val laranja = 0xffF07B07

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // button
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(50.dp)
                .padding(4.dp)
                .clickable { showDropdown = !showDropdown },
//            .clickable { showDropdown = !showDropdown },
            contentAlignment = Alignment.Center
        ) {
            Text(text = itemList[selectedIndex].dificuldade, modifier = Modifier.padding(3.dp))
        }

        // dropdown list
        Box(
            modifier = Modifier.border(
                width = 1.dp,
                color = Color(laranja),
                shape = RoundedCornerShape(10.dp)
            ),
        ) {
            if (showDropdown) {
                Popup(
                    alignment = Alignment.TopCenter,
                    properties = PopupProperties(
                        excludeFromSystemGesture = true,
                    ),
                    // to dismiss on click outside
                    onDismissRequest = { showDropdown = false }
                ) {

                    Column(
                        modifier = Modifier
                            .height(90.dp)
                            .verticalScroll(state = scrollState)
                            .width(350.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        itemList.onEachIndexed { index, item ->
                            if (index != 0) {
                                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.White)
                                    .width(350.dp)
                                    .clickable {
                                        onItemClick(index)
                                        showDropdown = !showDropdown
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = item.dificuldade, fontFamily = Inter)
                            }
                        }

                    }
                }
            }
        }
    }
}