package br.com.fiap.ecocalc.repository

import br.com.fiap.ecocalc.model.CarbonEstimateRequest
import br.com.fiap.ecocalc.model.CarbonEstimateResponse
import br.com.fiap.ecocalc.network.RetrofitInstance

class CarbonRepository {
    private val apiKey = "Bearer lLgr41Eg1C2d46YFXsOV2Q"

    suspend fun getCarbonEstimate(request: CarbonEstimateRequest): CarbonEstimateResponse {
        return RetrofitInstance.api.getCarbonEstimate(apiKey, request)
    }
}