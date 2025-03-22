package br.com.fiap.ecocalc.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarroScreen(navController: NavController) {
    var selectedBrand by remember { mutableStateOf("") }
    var selectedModel by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }

    val brands = listOf("HONDA", "FIAT", "CHEVROLET", "VOLKSWAGEN", "FERRARI")
    val models = mapOf(
        "HONDA" to listOf("CITY", "CIVIC", "HRV"),
        "FIAT" to listOf("500", "PALIO", "UNO"),
        "CHEVROLET" to listOf("ONIX", "CORSA", "ONIX PLUS"),
        "VOLKSWAGEN" to listOf("GOL", "JETTA", "VOYAGE"),
        "FERRARI" to listOf("F40", "F8", "LAFERRARI")
    )

    val emissionFactors = mapOf(
        "HONDA" to 160,
        "FIAT" to 110,
        "CHEVROLET" to 125,
        "VOLKSWAGEN" to 130,
        "FERRARI" to 220
    )

    var showBrandDialog by remember { mutableStateOf(false) }
    var showModelDialog by remember { mutableStateOf(false) }
    var emissionResult by remember { mutableStateOf("") }


    val tips = listOf(
        "Dirija suavemente, mantenha velocidade constante e antecipe freadas para economizar até 1 tonelada de CO₂ por ano.",
        "Use o ventilador em vez do ar-condicionado para reduzir o consumo de combustível em até 10%.",
        "Mantenha a manutenção em dia; um motor bem cuidado pode reduzir o consumo de combustível e CO₂ em até 50%.",
        "Mantenha os pneus calibrados para melhorar o rendimento do carro e diminuir emissões de CO₂.",
        "Prefira veículos movidos a álcool ou biocombustíveis para reduzir em mais de 500 kg de CO₂ por ano.",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { navController.navigate("menu") },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(text = "Voltar", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Calcular CO2 - Carro",
            fontSize = 22.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = distance,
            onValueChange = { distance = it },
            label = { Text("Distância (km)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Escolha a marca do seu carro",
            fontSize = 20.sp
        )
        Button(
            onClick = { showBrandDialog = true },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = if (selectedBrand.isEmpty()) "Selecione a Marca" else selectedBrand,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        if (showBrandDialog) {
            SelectionDialog(
                title = "Selecionar Marca",
                items = brands,
                onDismissRequest = { showBrandDialog = false },
                onItemSelected = { brand ->
                    selectedBrand = brand
                    selectedModel = ""
                    showBrandDialog = false
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Escolha o modelo do seu carro")
        Button(
            onClick = { showModelDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (selectedModel.isEmpty()) "Selecione o Modelo" else selectedModel,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        if (showModelDialog) {
            val availableModels = models[selectedBrand] ?: emptyList()
            SelectionDialog(
                title = "Selecionar Modelo",
                items = availableModels,
                onDismissRequest = { showModelDialog = false },
                onItemSelected = { model ->
                    selectedModel = model
                    showModelDialog = false
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp).padding(top = 64.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                val distanceValue = distance.toFloatOrNull()
                if (distanceValue != null && selectedBrand.isNotEmpty()) {
                    val emissionFactor = emissionFactors[selectedBrand] ?: 0
                    val emission = (distanceValue * emissionFactor) / 1000
                    emissionResult = "Emissão estimada: ${emission} kg"
                } else {
                    emissionResult = "Por favor, preencha todos os campos corretamente."
                }
            }
        ) {
            Text("Calcular Emissões")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (emissionResult.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF7557AA))
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = emissionResult,
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            DicasComponent(tips)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SelectionDialog(
    title: String,
    items: List<String>,
    onDismissRequest: () -> Unit,
    onItemSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = title) },
        text = {
            Column {
                items.forEach { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemSelected(item)
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

@Composable
fun DicasComponent(tips: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE0E0E0))
            .shadow(elevation = 4.dp)
    ) {
        Text(
            text = "Dicas para Reduzir Emissões:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        tips.forEach { tip ->
            Text(
                text = "• $tip",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 5.dp)
            )
        }
    }
}
