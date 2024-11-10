package com.example.core.DTO_Tables

data class Sell(
    val clientId: Int,
    val gasStationId: Int,
    val firmId: Int,
    val fuelId: Int,
    val sellTime: String,
    val litersAmount: Float
)
