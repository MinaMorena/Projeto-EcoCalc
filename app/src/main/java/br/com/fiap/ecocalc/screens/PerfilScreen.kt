package br.com.fiap.ecocalc.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.ecocalc.model.FlightEstimate
import br.com.fiap.ecocalc.viewmodel.CarbonHistoryViewModel
import br.com.fiap.ecocalc.viewmodel.CarbonHistoryViewModelFactory

@Composable
fun PerfilScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: CarbonHistoryViewModel = viewModel(factory = CarbonHistoryViewModelFactory(context))
    val flightEstimates by viewModel.flightEstimates.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF009688))
        .padding(32.dp)
    ) {
        Column {
            Text(
                text = "CO2 Production History",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF01776C))
            ) {
                LazyColumn {
                    items(flightEstimates) { flightEstimate ->
                        FlightEstimateRow(flightEstimate)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("menu") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Return", fontSize = 20.sp, color = Color(0xFF004741))
            }
        }
    }
}

@Composable
fun FlightEstimateRow(flightEstimate: FlightEstimate) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .height(56.dp)
        .background(color = Color(0xFF026156), shape = MaterialTheme.shapes.medium)
        .border(1.dp, Color(0xFF014B42), shape = MaterialTheme.shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = flightEstimate.departureAirport, modifier = Modifier.weight(0.5f).padding(start = 2.dp), color = Color.White)
        Text(text = flightEstimate.destinationAirport, modifier = Modifier.weight(0.5f), color = Color.White)
        Text(text = flightEstimate.passengers.toString(), modifier = Modifier.weight(0.5f), color = Color.White)
        Text(text = flightEstimate.cabinClass, modifier = Modifier.weight(1f), color = Color.White)
        Text(text = "${flightEstimate.carbonKg} kg", modifier = Modifier.weight(1f), color = Color.White)
    }
}