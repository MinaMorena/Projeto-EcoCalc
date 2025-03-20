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