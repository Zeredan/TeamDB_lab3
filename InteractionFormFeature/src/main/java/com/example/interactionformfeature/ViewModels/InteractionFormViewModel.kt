package com.example.interactionformfeature.ViewModels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.core.graphics.set
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.DTO_Tables.Client
import com.example.core.DTO_Tables.Firm
import com.example.core.DTO_Tables.FirmHasFuel
import com.example.core.DTO_Tables.Fuel
import com.example.core.DTO_Tables.GasStation
import com.example.core.DTO_Tables.Sell
import com.example.core.TableInteractionInfo.Order
import com.example.core.TableInteractionInfo.TableInteraction
import com.example.interactionformfeature.Dagger.DaggerInteractionFormFeatureComponent
import com.example.interactionformfeature.Dagger.InteractionFormFeatureComponent
import com.example.interactionformfeature.Retrofit.BackendCommunicationService
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class InteractionFormViewModel(savedStateHandle: SavedStateHandle) : ViewModel(){
    val interactionFlow = savedStateHandle.getStateFlow("interaction", "{}").map {
        try{
            Gson().fromJson(it, TableInteraction::class.java)
        }
        catch (e: Exception){
            null
        }
    }
    val textValues = mutableStateListOf<String>()

    lateinit var backendCommunicationService : BackendCommunicationService

        init {
            try {
                val resolver = DaggerInteractionFormFeatureComponent.builder()
                    .addUrl("")
                    .build()
                backendCommunicationService = resolver.resolveRetrofitApi()
            }
            catch (e: Exception){

            }
        }

    fun executeFormSubmission(interaction: TableInteraction){
        viewModelScope.launch {
            try {
                when (interaction.order) {
                    Order.Client_insert -> {
                        val client = Client(
                            textValues[0],
                            textValues[1],
                            textValues[2]
                        )
                        backendCommunicationService.insertClient(client)
                    }

                    Order.Client_update -> {
                        val client = Client(
                            textValues[1],
                            textValues[2],
                            textValues[3]
                        )
                        backendCommunicationService.updateClient(textValues[0].toInt(), client)
                    }

                    Order.Client_delete -> {
                        backendCommunicationService.deleteClient(textValues[0].toInt())
                    }

                    Order.Firm_insert -> {
                        val firm = Firm(
                            textValues[0],
                            textValues[1],
                            textValues[2]
                        )
                        backendCommunicationService.insertFirm(firm)
                    }

                    Order.Firm_update -> {
                        val firm = Firm(
                            textValues[1],
                            textValues[2],
                            textValues[3]
                        )
                        backendCommunicationService.updateFirm(textValues[0].toInt(), firm)
                    }

                    Order.Firm_delete -> {
                        backendCommunicationService.deleteFirm(textValues[0].toInt())
                    }

                    Order.FirmHasFuel_insert -> {
                        val fhf = FirmHasFuel(
                            textValues[0].toInt(),
                            textValues[1].toInt(),
                        )
                        backendCommunicationService.insertFirmHasFuel(fhf)
                    }

                    Order.FirmHasFuel_update -> {
                        val fhf = FirmHasFuel(
                            textValues[0].toInt(),
                            textValues[1].toInt(),
                        )
                        val newfhf = FirmHasFuel(
                            textValues[2].toInt(),
                            textValues[3].toInt(),
                        )
                        backendCommunicationService.updateFirmHasFuel(
                            mapOf(
                                "firmId" to fhf.firmId,
                                "fuelId" to fhf.fuelId
                            ), newfhf
                        )
                    }

                    Order.FirmHasFuel_delete -> {
                        val fhf = FirmHasFuel(
                            textValues[0].toInt(),
                            textValues[1].toInt(),
                        )
                        backendCommunicationService.deleteFirmHasFuel(
                            mapOf(
                                "firmId" to fhf.firmId,
                                "fuelId" to fhf.fuelId
                            )
                        )
                    }

                    Order.Fuel_insert -> {
                        val fuel = Fuel(
                            textValues[0],
                            textValues[1],
                            textValues[2].toFloat(),
                        )
                        backendCommunicationService.insertFuel(fuel)
                    }

                    Order.Fuel_update -> {
                        val fuel = Fuel(
                            textValues[1],
                            textValues[2],
                            textValues[3].toFloat(),
                        )
                        backendCommunicationService.updateFuel(textValues[0].toInt(), fuel)
                    }

                    Order.Fuel_delete -> {
                        backendCommunicationService.deleteFuel(textValues[0].toInt())
                    }

                    Order.GasStation_insert -> {
                        val gs = GasStation(
                            textValues[0],
                            textValues[1].toInt()
                        )
                        backendCommunicationService.insertGasStation(gs)
                    }

                    Order.GasStation_update -> {
                        val gs = GasStation(
                            textValues[1],
                            textValues[2].toInt()
                        )
                        backendCommunicationService.updateGasStation(textValues[0].toInt(), gs)
                    }

                    Order.GasStation_delete -> {
                        backendCommunicationService.deleteGasStation(textValues[0].toInt())
                    }

                    Order.Sell_insert -> {
                        val sell = Sell(
                            textValues[0].toInt(),
                            textValues[1].toInt(),
                            textValues[2].toInt(),
                            textValues[3].toInt(),
                            textValues[4],
                            textValues[5].toFloat(),
                        )
                        backendCommunicationService.insertSell(sell)
                    }

                    Order.Sell_update -> {
                        val sell = Sell(
                            textValues[1].toInt(),
                            textValues[2].toInt(),
                            textValues[3].toInt(),
                            textValues[4].toInt(),
                            textValues[5],
                            textValues[6].toFloat(),
                        )
                        backendCommunicationService.updateSell(textValues[0].toInt(), sell)
                    }

                    Order.Sell_delete -> {
                        backendCommunicationService.deleteSell(textValues[0].toInt())
                    }

                    else -> {}
                }
            }
            catch (e: Exception){
            }
        }
    }
}