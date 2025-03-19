package br.com.fiap.ecocalc.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    Box( modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF009688))
        .padding(32.dp)
    ) {
        Text(
            text = "MENU",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
//                passando na rota do perfil um parametro, no caso um nome
                onClick = { navController.navigate("perfil")},
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 200.dp, height = 48.dp)
            ) {
                Text(text = "Perfil", fontSize = 20.sp, color = Color.Blue)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("carros") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 200.dp, height = 48.dp)
            ) {
                Text(text = "Carros", fontSize = 20.sp, color = Color.Blue)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("aereos") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 200.dp, height = 48.dp)
            ) {
                Text(text = "Aviões", fontSize = 20.sp, color = Color.Blue)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("login") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 200.dp, height = 48.dp)
            ) {
                Text(text = "Sair", fontSize = 20.sp, color = Color.Blue)
            }
        }
    }
}

//@Preview
//@Composable
//fun MenuScreenPreview() {
//    MenuScreen()
//}