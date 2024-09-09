package com.drew.workingwithpasswords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.drew.workingwithpasswords.ui.theme.WorkingWithPasswordsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkingWithPasswordsTheme {
                    Box(modifier = Modifier.fillMaxSize()) {
                        PasswordItem(modifier = Modifier.align(Alignment.Center))

                }
            }
        }
    }
}

@Composable
fun PasswordItem(
    modifier: Modifier = Modifier,

) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }
   Column(
       modifier = modifier.padding(16.dp),
       verticalArrangement = Arrangement.spacedBy(16.dp),
       horizontalAlignment = Alignment.CenterHorizontally) {
       OutlinedTextField(
           modifier = modifier.fillMaxWidth(),
           value = value,
           onValueChange = {
               value = it
           },
           visualTransformation = if (isPasswordVisible) VisualTransformation.None
           else PasswordVisualTransformation(),
           placeholder = {
               Text(
                   modifier = Modifier.fillMaxWidth(),
                   text = "Password"
               )
           },
           trailingIcon = {
               IconButton(
                   onClick = {
                       isPasswordVisible = !isPasswordVisible
                   }
               ) {
                   Icon(
                       painter = if (isPasswordVisible) painterResource(R.drawable.baseline_visibility_24)
                       else painterResource(R.drawable.baseline_visibility_off_24),
                       contentDescription = if (isPasswordVisible) "Hide password"
                       else "Show password"
                   )
               }
           }
       )
       Text(
           modifier = Modifier.fillMaxWidth(),
           text = if (value.isPasswordValid(value)) "Password is valid" else "Password is not valid"
       )
   }
}

fun String.isPasswordValid(password: String): Boolean {
    return Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
        .matches(password)
}

fun String.isPasswordValid2(password: String): Boolean {
    return Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
        .matches(this)
}

@Preview(showBackground = true)
@Composable
fun PasswordPreview() {
    WorkingWithPasswordsTheme {
        PasswordItem()
    }
}