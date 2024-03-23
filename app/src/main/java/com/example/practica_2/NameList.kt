import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NameList() {
    var name by remember { mutableStateOf("") }
    var names by remember { mutableStateOf(listOf<String>()) } // Lista para almacenar nombres

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        FormTimer(10)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = name,
                onValueChange = { text ->
                    name = text
                },
                placeholder = { Text("Enter name") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        names = names + name
                        name = ""
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn {
            items(names) { name ->
                Text(text = name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NameListPreview() {
    NameList()
}
