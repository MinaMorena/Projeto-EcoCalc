package br.com.fiap.ecocalc.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.ecocalc.model.FlightEstimate

@Dao
interface FlightEstimateDao {
   @Insert
   fun insert(flightEstimate: FlightEstimate): Long

    @Query("SELECT * FROM flight_estimates")
   fun getAll(): List<FlightEstimate>
}
