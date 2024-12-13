package com.example.brainybunch.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.brainybunch.R
import com.example.brainybunch.component.HintPasswordField
import com.example.brainybunch.component.HintTextField
import com.example.brainybunch.component.MyState
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val state by viewModel.state.collectAsState()
    var email by remember {
        mutableStateOf("")
    }

    var pass by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF50E94D))
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(128.dp))

            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = "Login Your", style = MaterialTheme.typography.bodyMedium,
                fontSize = 38.sp,
                color = Color(0xFF323142),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = "Account", style = MaterialTheme.typography.bodyMedium,
                fontSize = 38.sp,
                color = Color(0xFF323142),
                fontWeight = FontWeight.Bold
            )

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 36.dp)
                        .clip(RoundedCornerShape(topStart = 86.dp))
                        .background(Color(0xFF3D3D3D))
                ) {
                    Column(modifier = Modifier.padding(top = 72.dp, start = 24.dp, end = 24.dp)) {

                        //EMAIL FIELD
                        HintTextField(hint = "Email", onValueChange = { email = it }, value = email)

                        Spacer(modifier = Modifier.height(12.dp))

                        //PASSWORD FIELD
                        HintPasswordField(
                            hint = "Password",
                            onValueChange = { pass = it },
                            value = pass
                        )

                        Spacer(modifier = Modifier.height(12.dp))


                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Forget Password?", style = MaterialTheme.typography.bodyMedium,
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        //LOGIN BUTTON
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp),
                            shape = RoundedCornerShape(24.dp),
                            onClick = { viewModel.login(email, pass) },
                            colors = ButtonDefaults.buttonColors(Color(0xFF3DC53A))
                        ) {
                            Text(
                                text = "Login", style = MaterialTheme.typography.bodyMedium,
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Don't have any account?",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                modifier = Modifier.clickable {
                                    navController.navigate("register")
                                },
                                text = "REGISTER", style = MaterialTheme.typography.bodyMedium,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )

                        }


                    }
                }
                AsyncImage(
                    modifier = Modifier
                        .height(74.dp)
                        .align(Alignment.TopEnd),
                    model = R.drawable.shape_circle,
                    contentDescription = "",
                )

            }
        }
    }

    //LOADING HANDLE
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is MyState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(64.dp))
                }
            }

            is MyState.Success -> {
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                    },
                    title = {
                        Text(text = "Success")
                    },
                    text = {
                        Text(text = "Login successful. Please Wait!")
                    }
                )
                LaunchedEffect(Unit) {
                    delay(2000)
                    navController.navigate("home")
                }

            }

            is MyState.Error -> {
                val errorMessage = (state as MyState.Error).message
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(
                            onClick = { viewModel.resetState() },
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF3F4E3E)
                            )
                        ) {
                            Text("OK")
                        }
                    },
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(text = errorMessage ?: "Unknown error occurred.")
                    }
                )
            }

            else -> { //
            }
        }
    }
}