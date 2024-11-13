package com.example.core.DTO_Tables

data class Sale(
    val cardId: Int,
    val gasStationId: Int,
    val firmId: Int,
    val fuelId: Int,
    val saleDate: String,
    val liters: Float
)
