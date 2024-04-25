package com.alejandro.calculadoradeimc

import android.graphics.Color
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.text.method.Touch
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandro.calculadoradeimc.calculo.CalcularIMC
import com.alejandro.calculadoradeimc.ui.theme.CalculadoraDeIMCTheme
import com.alejandro.calculadoradeimc.ui.theme.DARK_BLUE
import com.alejandro.calculadoradeimc.ui.theme.LIGHT_BLUE
import com.alejandro.calculadoradeimc.ui.theme.WHITE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraDeIMCTheme {
                CalculadoraDeIMC()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraDeIMC() {

    var peso by remember {
        mutableStateOf("")
    }
    
    var altura by remember {
        mutableStateOf("")
    }

    var textoResposta by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val calculoIMC = CalcularIMC()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Calculadora de IMC", color = WHITE, fontWeight = FontWeight.Bold)},
                colors = TopAppBarDefaults.smallTopAppBarColors(LIGHT_BLUE),
                actions = {
                    IconButton(onClick = {
                        peso = ""
                        altura = ""
                        textoResposta = ""
                    }) {
                        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_refresh), contentDescription = "Limpar os campos")
                    }
                }
            )
        }
    ) {
        Box(Modifier.fillMaxWidth().padding(it).background(WHITE)) {
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {

                Text("Adicionar os valores", Modifier.padding(50.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold, color = LIGHT_BLUE)

                OutlinedTextField(value = peso, onValueChange = {peso = it}, label = {Text("Peso (Kg)")},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = LIGHT_BLUE,
                        focusedBorderColor = LIGHT_BLUE,
                        focusedLabelColor = DARK_BLUE
                    ),
                    textStyle = TextStyle(DARK_BLUE, 18.sp),
                    maxLines = 1,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp, 20.dp, 0.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(value = altura, onValueChange = {altura = it}, label = {Text("Altura")},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = LIGHT_BLUE,
                        focusedBorderColor = LIGHT_BLUE,
                        focusedLabelColor = DARK_BLUE
                    ),
                    textStyle = TextStyle(DARK_BLUE, 18.sp),
                    maxLines = 1,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Button(
                    onClick = {
                        if (peso.isEmpty() || altura.isEmpty()) {
                            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                        } else {
                            textoResposta = calculoIMC.calculoIMC(peso, altura)
                        }
                    },
                    Modifier.fillMaxWidth().padding(20.dp, 10.dp, 20.dp, 20.dp).height(60.dp),
                    colors = ButtonDefaults.buttonColors(LIGHT_BLUE, contentColor = WHITE)
                ) {
                    Text("CALCULAR O IMC", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Text(textoResposta, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DARK_BLUE)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    CalculadoraDeIMCTheme {
        CalculadoraDeIMC()
    }
}