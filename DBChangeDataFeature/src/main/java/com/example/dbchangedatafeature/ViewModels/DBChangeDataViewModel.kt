package com.example.dbchangedatafeature.ViewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.TableInteractionInfo.TableInfo
import com.google.gson.Gson
import kotlinx.coroutines.flow.map

class DBChangeDataViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val selectedTable = savedStateHandle.getStateFlow("table", "").map{
        try {
            Gson().fromJson(it, TableInfo::class.java)
        }
        catch (e: Exception){
            null
        }
    }
}