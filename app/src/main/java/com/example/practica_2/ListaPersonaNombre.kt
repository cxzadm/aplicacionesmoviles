import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.unit.sp
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
        // Aquí se muestra el temporizador antes del formulario
        FormTimer(
            duration = timeLeft,
            onPause = { isPaused = true },
            onReset = {
                isPaused = false
                timeLeft = 10
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            enabled = timeLeft > 0 // Deshabilitar el TextField cuando el tiempo llegue a 0
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth(),
            enabled = timeLeft > 0 // Deshabilitar el TextField cuando el tiempo llegue a 0
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
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = timeLeft > 0 // Deshabilitar el botón cuando el tiempo llegue a 0
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        personas.forEach { person ->
            Text(text = "ID: ${person.id}, Nombre: ${person.name}, Edad: ${person.age}")
        }
    }
}

@Composable
fun FormTimer(
    duration: Int,
    onPause: () -> Unit = {},
    onReset: () -> Unit = {},
    onComplete: () -> Unit = {}
) {
    var timeLeft by remember { mutableStateOf(duration) }
    var isPaused by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0 && !isPaused) {
            delay(1000L)
            timeLeft--
        }
        onComplete()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Time left: ${timeLeft.toString()}",
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onPause() },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
        Button(
            onClick = { onReset() },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegistroPersonas() {
    RegistroPersonas()
}
