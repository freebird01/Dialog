package com.example.dialog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.dialog.ui.theme.DialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DialogExample(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogExample(name: String, modifier: Modifier = Modifier) {
    val shouldShowDialog = remember {
        mutableStateOf(false)
    }

    val myButtonTextColor = remember {
        mutableStateOf(Color.White)
    }
    val currentContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                shouldShowDialog.value = true
            }
        ) {
            Text(text = "Show Dialog Message", color = myButtonTextColor.value)
        }

        if (shouldShowDialog.value) {
            AlertDialog(
                onDismissRequest = { shouldShowDialog.value = true },
                confirmButton = {
                    Button(
                        onClick = {
                            Toast.makeText(
                                currentContext,
                                "Action Performed",
                                Toast.LENGTH_SHORT
                            ).show()
                            shouldShowDialog.value = false
                            myButtonTextColor.value = Color.Red
                        },
                        colors = ButtonDefaults.buttonColors(Color.White)
                    ) { Text(text = "YES", color = Color.Green) }
                },
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 2.dp, bottom = 2.dp),
                dismissButton = {
                    Button(
                        onClick = {
                            Toast.makeText(
                                currentContext,
                                "Action Dismissed",
                                Toast.LENGTH_SHORT
                            ).show()
                            shouldShowDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(Color.White)
                    ) { Text(text = "NO", color = Color.Red) }
                },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.warning),
                            contentDescription = "",
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Dialog Message", fontSize = 20.sp, color = Color.Red)
                    }
                },
                text = {
                    Text(
                        "Do you want to change text of the button?",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                },
                containerColor = Color(0xFF03A9F4),
                shape = RoundedCornerShape(0.dp)
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DialogTheme {
        DialogExample("Android")
    }
}