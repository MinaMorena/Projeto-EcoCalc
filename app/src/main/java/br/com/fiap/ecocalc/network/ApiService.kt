package br.com.fiap.ecocalc.network

import br.com.fiap.ecocalc.model.CarbonEstimateRequest
import br.com.fiap.ecocalc.model.CarbonEstimateResponse
import br.com.fiap.ecocalc.model.VehicleEstimateRequest
import br.com.fiap.ecocalc.model.Vehicle
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("estimates")
    suspend fun getCarbonEstimate(
        @Header("Authorization") authHeader: String,
        @Body request: CarbonEstimateRequest
    ): CarbonEstimateResponse


    @Headers("Content-Type: application/json")
    @POST("estimates/vehicle")
    suspend fun getVehicleEstimate(
        @Header("Authorization") authHeader: String,
        @Body request: VehicleEstimateRequest
    ): CarbonEstimateResponse

    @Headers("Content-Type: application/json")
    @GET("vehicles/makes")
    suspend fun getVehicleMakes(
        @Header("Authorization") authHeader: String
    ): List<Vehicle>

    @Headers("Content-Type: application/json")
    @GET("vehicles/makes/{vehicleId}/models")
    suspend fun getVehicleModels(
        @Path("vehicleId") vehicleId: String,
        @Header("Authorization") authHeader: String
    ): List<Vehicle>
}


