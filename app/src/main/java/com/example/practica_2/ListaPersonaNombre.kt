import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import java.util.*

class Person(val name: String, val age: Int) {
    val id: UUID = UUID.randomUUID()
}

@Composable
fun RegistroPersonas() {
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    val personas = remember { mutableStateListOf<Person>() }

    var timeLeft by remember { mutableStateOf(10) }
    var isPaused by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0) {
            delay(1000L)
            if (!isPaused) {
                timeLeft--
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Time left: ${timeLeft.toString()}",
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { isPaused = true }
                ) {
                    Text("Pausar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        timeLeft = 10
                        isPaused = false
                    }
                ) {
                    Text("Reiniciar")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nombre.isNotBlank() && edad.isNotBlank()) {
                    val ageInt = edad.toIntOrNull()
                    if (ageInt != null) {
                        val person = Person(nombre, edad.toInt())
                        personas.add(person)
                        nombre = ""
                        edad = ""
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        personas.forEach { person ->
            Text(text = "ID: ${person.id}, Nombre: ${person.name}, Edad: ${person.age}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegistroPersonas() {
    RegistroPersonas()
}
