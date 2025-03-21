package br.com.fiap.ecocalc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column // Adicione esta importação
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF009688))
        .padding(32.dp)
    ) {
        Column {
            Text(
                text = "ECOCALC",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 32.dp)
            )
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Bem vindo ao EcoCalc, sua calculadora de emissões de CO₂",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Estamos felizes em tê-lo conosco! O ECOCALC é a sua nova calculadora de emissões de CO₂, projetada para ajudá-lo a entender e reduzir o impacto ambiental das suas viagens aéreas e de carro.",
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Nosso objetivo é capacitá-lo a tomar decisões mais conscientes em relação às suas viagens, contribuindo assim para um planeta mais saudável. Vamos juntos nessa jornada rumo a um futuro mais verde!",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = { navController.navigate("menu") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 36.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    text = "Vamos começar",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
                    )
            }
        }
    }
}