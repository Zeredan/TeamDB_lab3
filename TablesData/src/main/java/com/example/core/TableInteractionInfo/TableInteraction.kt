package com.example.core.TableInteractionInfo

data class TableInteraction(
    val name: String?,
    val type: InteractionType?,
    val fields: List<String>?,
    val order: Order?
)
