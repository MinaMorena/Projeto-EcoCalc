package br.com.fiap.ecocalc.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.ecocalc.model.CarbonEstimateRequest
import br.com.fiap.ecocalc.model.FlightEstimate
import br.com.fiap.ecocalc.model.FlightLeg
import br.com.fiap.ecocalc.viewmodel.CarbonViewModel
import br.com.fiap.ecocalc.database.AppDatabase
import br.com.fiap.ecocalc.repository.FlightEstimateRepository
import kotlinx.coroutines.delay

data class Airport(val name: String, val code: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AereoScreen(navController: NavController) {
    val viewModel: CarbonViewModel = viewModel()
    val carbonEstimate by viewModel.carbonEstimate.collectAsState()

    var passengers by remember { mutableStateOf(1) }
    var departureAirport by remember { mutableStateOf("") }
    var departureAirportName by remember { mutableStateOf("") }
    var destinationAirport by remember { mutableStateOf("") }
    var destinationAirportName by remember { mutableStateOf("") }
    var cabinClass by remember { mutableStateOf("economy") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    val airports = listOf(
        Airport("Galeão - Rio de Janeiro", "GIG"),
        Airport("Santos Dumont - Rio de Janeiro", "SDU"),
        Airport("Guarulhos - São Paulo", "GRU"),
        Airport("Tancredo Neves - Belo Horizonte", "CNF"),
        Airport("Hercilio Luz - Florianopolis", "FLN"),
        Airport("Pinto Martins - Fortaleza", "FOR"),
        Airport("Eduardo Gomes - Manaus", "MAO"),
        Airport("Santa Genoveva - Goiania", "GYN"),
        Airport("J. Kubitschek - Brasilia", "BSB"),
        Airport("Charles de Gaulle - Paris", "CDG"),
        Airport("Orly - Paris", "ORY"),
        Airport("Fiumicino - Roma", "FCO"),
        Airport("Lisboa - Lisboa", "LIS"),
        Airport("Adolfo Suarez-Barajas - Madri", "MAD"),
        Airport("Stewart International - Nova York", "SWF"),
        Airport("John F Kennedy Intl - Nova York", "JFK"),
        Airport("Miami Intl. - Miami", "MIA"),
        Airport("Lester B. Pearson Intl - Toronto", "YYZ"),
        Airport("Cruzeiro do Sul Intl. - Africa do Sul", "CZS")
    )

    var showDepartureDialog by remember { mutableStateOf(false) }
    var showDestinationDialog by remember { mutableStateOf(false) }


    val context = LocalContext.current
    val flightEstimateRepository = FlightEstimateRepository(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "CO2 Emissions for Flights",
            fontSize = 26.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { if (passengers > 1) passengers-- },
            ) {
                Text("-", fontSize = 20.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = passengers.toString(),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { passengers++ },
            ) {
                Text("+", fontSize = 20.sp, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            Button(
                onClick = { showDepartureDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (departureAirportName.isEmpty()) "Departure Airport" else departureAirportName,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
            if (showDepartureDialog) {
                AirportSelectionDialog(
                    title = "Select Departure Airport",
                    airports = airports,
                    onDismissRequest = { showDepartureDialog = false },
                    onAirportSelected = { airport ->
                        departureAirport = airport.code
                        departureAirportName = airport.name
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { showDestinationDialog = true },
            ) {
                Text(
                    text = if (destinationAirportName.isEmpty()) "Destination Airport" else destinationAirportName,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
            if (showDestinationDialog) {
                AirportSelectionDialog(
                    title = "Select Destination Airport",
                    airports = airports,
                    onDismissRequest = { showDestinationDialog = false },
                    onAirportSelected = { airport ->
                        destinationAirport = airport.code
                        destinationAirportName = airport.name
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            RadioButton(
                selected = cabinClass == "economy",
                onClick = { cabinClass = "economy" }
            )
            Text("Economy", modifier = Modifier.align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = cabinClass == "premium",
                onClick = { cabinClass = "premium" }
            )
            Text("Premium", modifier = Modifier.align(Alignment.CenterVertically))
        }
        Spacer(modifier = Modifier.height(16.dp))
        isButtonEnabled = departureAirport.isNotEmpty() && destinationAirport.isNotEmpty()
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                val request = CarbonEstimateRequest(
                    passengers = passengers,
                    legs = listOf(
                        FlightLeg(departureAirport, destinationAirport, cabinClass)
                    )
                )
                viewModel.getCarbonEstimate(request)
            },
            enabled = isButtonEnabled // Habilitar o botão apenas se ambos os aeroportos estiverem selecionados
        ) {
            Text("Get Carbon Estimate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        carbonEstimate?.let {
            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF7557AA))
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Carbon Estimate:",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "${it.data.attributes.carbon_kg} kg",
                            color = Color.White,
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
                    LaunchedEffect(carbonEstimate) {
                        carbonEstimate?.let {
                            delay(3000) // Espera por 3 segundos
                            val flightEstimate = FlightEstimate(
                                departureAirport = departureAirport,
                                destinationAirport = destinationAirport,
                                passengers = passengers,
                                cabinClass = cabinClass,
                                carbonKg = it.data.attributes.carbon_kg
                            )
                            flightEstimateRepository.insert(flightEstimate = flightEstimate)
                        }
                    }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("menu") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Return", fontSize = 12.sp, color = Color.White)
        }
    }
}

@Composable
fun AirportSelectionDialog(
    title: String,
    airports: List<Airport>,
    onDismissRequest: () -> Unit,
    onAirportSelected: (Airport) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = title) },
        text = {
            Column {
                airports.forEach { airport ->
                    Text(
                        text = airport.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onAirportSelected(airport)
                                onDismissRequest()
                            }
                            .padding(8.dp)
                    )
                }
            }
        },
        confirmButton = {}
    )
}