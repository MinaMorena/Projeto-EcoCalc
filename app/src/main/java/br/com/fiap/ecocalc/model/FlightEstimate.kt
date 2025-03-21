package br.com.fiap.ecocalc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flight_estimates")
data class FlightEstimate(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val departureAirport: String,
    val destinationAirport: String,
    val passengers: Int,
    val cabinClass: String,
    val carbonKg: Double
)