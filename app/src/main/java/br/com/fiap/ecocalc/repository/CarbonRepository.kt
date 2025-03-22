package br.com.fiap.ecocalc.repository

import br.com.fiap.ecocalc.model.CarbonEstimateRequest
import br.com.fiap.ecocalc.model.CarbonEstimateResponse
import br.com.fiap.ecocalc.model.VehicleEstimateRequest
import br.com.fiap.ecocalc.model.Vehicle
import br.com.fiap.ecocalc.network.RetrofitInstance

class CarbonRepository {
    private val apiKey = "Bearer lLgr41Eg1C2d46YFXsOV2Q"


    suspend fun getCarbonEstimate(request: CarbonEstimateRequest): CarbonEstimateResponse {
        return RetrofitInstance.api.getCarbonEstimate(apiKey, request)
    }

    suspend fun getVehicleEstimate(request: VehicleEstimateRequest): CarbonEstimateResponse {
        return RetrofitInstance.api.getVehicleEstimate(apiKey, request)
    }

    suspend fun getVehicleMakes(apiKey: String): List<Vehicle> {
        return RetrofitInstance.api.getVehicleMakes(apiKey)
    }

    suspend fun getVehicleModels(vehicleId: String, apiKey: String): List<Vehicle> {
        return RetrofitInstance.api.getVehicleModels(vehicleId, apiKey)
    }
}
