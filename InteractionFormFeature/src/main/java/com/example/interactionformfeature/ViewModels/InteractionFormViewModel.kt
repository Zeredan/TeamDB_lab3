package com.example.interactionformfeature.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.TableInteractionInfo.TableInteraction
import com.example.interactionformfeature.Dagger.DaggerInteractionFormFeatureComponent
import com.example.interactionformfeature.UseCases.SendFormUseCase.SendFormUseCase
import com.google.gson.Gson
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
    var baseUrl by mutableStateOf("https://")

    fun executeFormSubmission(interaction: TableInteraction){
        try {
            val uc = SendFormUseCase(
                DaggerInteractionFormFeatureComponent.builder()
                    .addUrl(baseUrl)
                    .build()
                    .resolveRetrofitApi(),
                textValues
            )
            viewModelScope.launch {
                uc.sendData(interaction)
            }
        }
        catch (e: Exception){
            println("retrofit creation error: $e")
        }
    }
}