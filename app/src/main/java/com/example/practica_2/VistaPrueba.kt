package com.example.practica_2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BotonYTextField() {
    var texto1 by remember { mutableStateOf("") }
    var texto2 by remember { mutableStateOf("") }
    val listaTexto1 = remember { mutableStateListOf<String>() }
    val listaTexto2 = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.fillMaxSize()) {
        // Primera fila: Textfield, botón de ingreso y botón de borrar para la lista 1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = texto1,
                onValueChange = { texto1 = it },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    listaTexto1.add(texto1)
                    texto1 = ""
                },
                modifier = Modifier.padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "Ingresar a lista 1")
            }
            IconButton(
                onClick = { listaTexto1.clear() },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Icon(Icons.Default.Clear, contentDescription = "Limpiar lista 1")
            }
        }

        // Segunda fila: Lista 1
        Column(modifier = Modifier.fillMaxWidth()) {
            for (item in listaTexto1) {
                Text(text = item)
            }
        }

        // Tercera fila: Textfield, botón de ingreso y botón de borrar para la lista 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = texto2,
                onValueChange = { texto2 = it },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    listaTexto2.add(texto2)
                    texto2 = ""
                },
                modifier = Modifier.padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "Ingresar a lista 2")
            }
            IconButton(
                onClick = { listaTexto2.clear() },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Icon(Icons.Default.Clear, contentDescription = "Limpiar lista 2")
            }
        }

        // Cuarta fila: Lista 2
        Column(modifier = Modifier.fillMaxWidth()) {
            for (item in listaTexto2) {
                Text(text = item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBotonYTextField() {
    BotonYTextField()
}
