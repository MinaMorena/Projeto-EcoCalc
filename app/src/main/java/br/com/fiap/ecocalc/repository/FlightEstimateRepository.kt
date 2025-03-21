package br.com.fiap.ecocalc.repository

import android.content.Context
import br.com.fiap.ecocalc.database.AppDatabase
import br.com.fiap.ecocalc.model.FlightEstimate

class FlightEstimateRepository(context: Context) {

    private val db = AppDatabase.getDatabase(context).flightEstimateDao()

    fun insert(flightEstimate: FlightEstimate): Long {
        return db.insert(flightEstimate)
    }

    fun getAllFlightEstimates(): List<FlightEstimate> {
        return db.getAll()
    }
}