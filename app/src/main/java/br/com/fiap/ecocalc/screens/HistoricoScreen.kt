package br.com.fiap.ecocalc.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecocalc.model.PesquisaHistorico


@Composable
fun HistoricoScreen(navController: NavController, pesquisas: List<PesquisaHistorico>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { navController.navigate("menu") },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(text = "Voltar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Histórico de Pesquisas",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (pesquisas.isEmpty()) {
            Text("Nenhuma pesquisa realizada ainda.", fontSize = 16.sp)
        } else {
            pesquisas.forEach { pesquisa ->
                PesquisaCard(pesquisa)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun PesquisaCard(pesquisa: PesquisaHistorico) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Marca: ${pesquisa.marca}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("Modelo: ${pesquisa.modelo}", fontSize = 16.sp)
            Text("Distância: ${pesquisa.distancia} km", fontSize = 16.sp)
            Text("Emissão CO₂: ${pesquisa.emissao} kg", fontSize = 16.sp)
        }
    }
}



