package com.dsiriarte.sendmessage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                color = MaterialTheme.colors.background
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(text = stringResource(id = R.string.title_message))
                    Row {
                        var phoneNumber: String by remember { mutableStateOf("") }
                        OutlinedTextField(
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                            value = phoneNumber,
                            onValueChange = {
                                phoneNumber = if (it.isEmpty()) {
                                    it
                                } else {
                                    when (it.toDoubleOrNull()) {
                                        null -> phoneNumber // old value
                                        else -> it // new value
                                    }
                                }
                            },
                            label = { Text(stringResource(id = R.string.phone_number)) }
                        )

                        Button(
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            onClick = {
                                if (phoneNumber.isNotEmpty()) {
                                    val url =
                                        "https://api.whatsapp.com/send?phone=+$phoneNumber"
                                    val i = Intent(Intent.ACTION_VIEW)
                                    i.data = Uri.parse(url)
                                    startActivity(i)
                                    Log.i("santiago", url)
                                }
                            }
                        ) {
                            Icon(
                                Icons.Filled.Send,
                                contentDescription = "Send",
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                        }
                    }
                }
            }
        }
    }
}
