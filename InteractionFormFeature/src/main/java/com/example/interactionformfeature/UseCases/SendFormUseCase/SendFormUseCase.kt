package com.example.interactionformfeature.UseCases.SendFormUseCase

import com.example.core.DTO_Tables.Client
import com.example.core.DTO_Tables.Firm
import com.example.core.DTO_Tables.FirmHasFuel
import com.example.core.DTO_Tables.Fuel
import com.example.core.DTO_Tables.GasStation
import com.example.core.DTO_Tables.Sale
import com.example.core.TableInteractionInfo.Order
import com.example.core.TableInteractionInfo.TableInteraction
import com.example.interactionformfeature.Retrofit.BackendCommunicationService

class SendFormUseCase(
    val backendCommunicationService: BackendCommunicationService,
    val textValues: List<String>
)
{
    suspend fun sendData(interaction: TableInteraction){
        try {
            println("ORDER: ${interaction.order}")
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
                        textValues[1].toInt(),
                        textValues[0]
                    )
                    backendCommunicationService.insertGasStation(gs)
                }

                Order.GasStation_update -> {
                    val gs = GasStation(
                        textValues[2].toInt(),
                        textValues[1]
                    )
                    backendCommunicationService.updateGasStation(textValues[1].toInt(), gs)
                }

                Order.GasStation_delete -> {
                    backendCommunicationService.deleteGasStation(textValues[1].toInt())
                }

                Order.Sell_insert -> {
                    val sell = Sale(
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
                    val sell = Sale(
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