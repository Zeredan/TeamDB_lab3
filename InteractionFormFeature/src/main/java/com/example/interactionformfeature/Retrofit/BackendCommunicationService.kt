package com.example.interactionformfeature.Retrofit


import com.example.core.DTO_Tables.Client
import com.example.core.DTO_Tables.Firm
import com.example.core.DTO_Tables.FirmHasFuel
import com.example.core.DTO_Tables.Fuel
import com.example.core.DTO_Tables.GasStation
import com.example.core.DTO_Tables.Sell
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface BackendCommunicationService {
    @POST("tables/firm")
    suspend fun insertFirm(@Body firm: Firm)

    @PATCH("tables/firm/{firmId}")
    suspend fun updateFirm(@Path("firmId") firmId: Int, @Body firm: Firm)

    @DELETE("tables/firm/{firmId}")
    suspend fun deleteFirm(@Path("firmId") firmId: Int)

    //================================================================================

    @POST("tables/gasStation")
    suspend fun insertGasStation(@Body gasStation: GasStation)

    @PATCH("tables/gasStation/{gasStationId}")
    suspend fun updateGasStation(@Path("gasStationId") gasStationId: Int, @Body gasStation: GasStation)

    @DELETE("tables/gasStation/{gasStationId}")
    suspend fun deleteGasStation(@Path("gasStationId") gasStationId: Int)

    //================================================================================

    @POST("tables/client")
    suspend fun insertClient(@Body client: Client)

    @PATCH("tables/client/{clientId}")
    suspend fun updateClient(@Path("clientId") clientId: Int, @Body client: Client)

    @DELETE("tables/client/{clientId}")
    suspend fun deleteClient(@Path("clientId") clientId: Int)

    //================================================================================

    @POST("tables/fuel")
    suspend fun insertFuel(@Body fuel: Fuel)

    @PATCH("tables/fuel/{fuelId}")
    suspend fun updateFuel(@Path("fuelId") fuelId: Int, @Body fuel: Fuel)

    @DELETE("tables/fuel/{fuelId}")
    suspend fun deleteFuel(@Path("fuelId") fuelId: Int)

    //================================================================================

    @POST("tables/firmHasFuel")
    suspend fun insertFirmHasFuel(@Body fhf: FirmHasFuel)

    @PATCH("tables/firmHasFuel")
    suspend fun updateFirmHasFuel(@QueryMap map: Map<String, Int>, @Body fhf: FirmHasFuel)

    @DELETE("tables/firmHasFuel")
    suspend fun deleteFirmHasFuel(@QueryMap map: Map<String, Int>)

    //================================================================================

    @POST("tables/sell")
    suspend fun insertSell(@Body sell: Sell)

    @PATCH("tables/sell/{sellId}")
    suspend fun updateSell(@Path("sellId") sellId: Int, @Body sell: Sell)

    @DELETE("tables/sell/{sellId}")
    suspend fun deleteSell(@Path("sellId") sellId: Int)
}