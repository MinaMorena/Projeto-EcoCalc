package br.com.fiap.ecocalc.network

import br.com.fiap.ecocalc.model.CarbonEstimateRequest
import br.com.fiap.ecocalc.model.CarbonEstimateResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("estimates")
    suspend fun getCarbonEstimate(
        @Header("Authorization") aunthHeader: String,
        @Body request: CarbonEstimateRequest
    ): CarbonEstimateResponse
}