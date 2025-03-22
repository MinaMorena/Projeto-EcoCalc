package br.com.fiap.ecocalc.model

data class FlightLeg(
    val departure_airport: String,
    val destination_airport: String,
    val cabin_class: String = "economy"
)

data class CarbonEstimateRequest(
    val type: String = "flight",
    val passengers: Int,
    val legs: List<FlightLeg>
)

data class Vehicle(
    val id: String,
    val name: String
)

data class PesquisaHistorico(
    val marca: String,
    val modelo: String,
    val distancia: Float,
    val emissao: Float
)


data class CarbonEstimateResponse(
    val data: CarbonData
)

data class CarbonData(
    val id: String,
    val type: String,
    val attributes: CarbonAttributes
)

data class CarbonAttributes(
    val carbon_g: Int,
    val carbon_lb: Double,
    val carbon_kg: Double,
    val carbon_mt: Double
)

data class VehicleEstimateRequest(
    val distance_unit: String, // Ex: "mi" ou "km"
    val distance_value: Int,    // Distância da viagem em unidades apropriadas
    val vehicle_model_id: String // ID do modelo do veículo
)

data class VehicleEstimateResponse(
    val carbon_offset: Float,    // Exemplo de atributo, ajuste conforme a resposta real
    val distance: Int,
    // Adicione outros campos conforme necessário
)

