package com.example.dbselectionfeature.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.core.TableInteractionInfo.InteractionType
import com.example.core.TableInteractionInfo.Order
import com.example.core.TableInteractionInfo.TableInfo
import com.example.core.TableInteractionInfo.TableInteraction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class DBSelectionViewModel() : ViewModel() {
    val tables = MutableStateFlow(
        listOf(
            TableInfo(
                "Client",
                listOf(
                    TableInteraction(
                        "insert",
                        InteractionType.Insert,
                        listOf(
                            "Фио",
                            "адрес",
                            "номер телефона"
                        ),
                        Order.Client_insert
                    ),
                    TableInteraction(
                        "update",
                        InteractionType.Update,
                        listOf(
                            "id Клиента",

                            "Фио(новое)",
                            "адрес(новое)",
                            "номер телефона(новое)"
                        ),
                        Order.Client_update
                    ),
                    TableInteraction(
                        "delete",
                        InteractionType.Delete,
                        listOf(
                            "id клиента",
                        ),
                        Order.Client_update
                    ),
                )
            ),
            TableInfo(
                "Firm",
                listOf(
                    TableInteraction(
                        "insert",
                        InteractionType.Insert,
                        listOf(
                            "Название",
                            "адрес",
                            "номер телефона"
                        ),
                        Order.Firm_insert
                    ),
                    TableInteraction(
                        "update",
                        InteractionType.Update,
                        listOf(
                            "id фирмы",

                            "Фио(новое)",
                            "адрес(новое)",
                            "номер телефона(новое)"
                        ),
                        Order.Firm_update
                    ),
                    TableInteraction(
                        "delete",
                        InteractionType.Delete,
                        listOf(
                            "id фирмы"
                        ),
                        Order.Firm_delete
                    ),
                )
            ),
            TableInfo(
                "FirmHasFuel",
                listOf(
                    TableInteraction(
                        "insert",
                        InteractionType.Insert,
                        listOf(
                            "id фирмы",
                            "id топлива",
                        ),
                        Order.FirmHasFuel_insert
                    ),
                    TableInteraction(
                        "update",
                        InteractionType.Update,
                        listOf(
                            "id фирмы",
                            "id топлива",
                            "id фирмы(новое)",
                            "id топлива(новое)",
                        ),
                        Order.FirmHasFuel_update
                    ),
                    TableInteraction(
                        "delete",
                        InteractionType.Delete,
                        listOf(
                            "id фирмы",
                            "id топлива",
                        ),
                        Order.FirmHasFuel_delete
                    ),
                )
            ),
            TableInfo(
                "Fuel",
                listOf(
                    TableInteraction(
                        "insert",
                        InteractionType.Insert,
                        listOf(
                            "Тип топлива",
                            "Единицы измерения",
                            "Цена"
                        ),
                        Order.Fuel_insert
                    ),
                    TableInteraction(
                        "update",
                        InteractionType.Update,
                        listOf(
                            "id топлива",

                            "Тип топлива(новое)",
                            "Единицы измерения(новое)",
                            "Цена(новое)"
                        ),
                        Order.Fuel_update
                    ),
                    TableInteraction(
                        "delete",
                        InteractionType.Delete,
                        listOf(
                            "id топлива",
                        ),
                        Order.Fuel_delete
                    ),
                )
            ),
            TableInfo(
                "GasStation",
                listOf(
                    TableInteraction(
                        "insert",
                        InteractionType.Insert,
                        listOf(
                            "адрес",
                            "id фирмы"
                        ),
                        Order.GasStation_insert
                    ),
                    TableInteraction(
                        "update",
                        InteractionType.Update,
                        listOf(
                            "id автозаправки",
                            "адрес",
                            "id фирмы"
                        ),
                        Order.GasStation_update
                    ),
                    TableInteraction(
                        "delete",
                        InteractionType.Delete,
                        listOf(
                            "id автозаправки"
                        ),
                        Order.GasStation_delete
                    ),
                )
            ),
            TableInfo(
                "Sell",
                listOf(
                    TableInteraction(
                        "insert",
                        InteractionType.Insert,
                        listOf(
                            "id клиента",
                            "id автозаправки",
                            "id фирмы",
                            "id топлива",
                            "дата продажи",
                            "количество литров"
                        ),
                        Order.Sell_insert
                    ),
                    TableInteraction(
                        "update",
                        InteractionType.Update,
                        listOf(
                            "id покупки",
                            "id клиента",
                            "id автозаправки",
                            "id фирмы",
                            "id топлива",
                            "дата продажи",
                            "количество литров"
                        ),
                        Order.Sell_update
                    ),
                    TableInteraction(
                        "delete",
                        InteractionType.Delete,
                        listOf(
                            "id покупки",
                        ),
                        Order.Sell_delete
                    ),
                )
            )
        )
    )
    val tableNames: Flow<List<String>> = tables.map{it.map{ it1 -> it1.name!!}}


    var currentTextFieldValue by mutableStateOf("")
        private set

    fun updateTextFieldValue(str: String) {
        currentTextFieldValue = str
    }
}