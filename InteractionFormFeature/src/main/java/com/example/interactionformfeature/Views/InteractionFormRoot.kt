package com.example.interactionformfeature.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.TableInteractionInfo.InteractionType
import com.example.interactionformfeature.ViewModels.InteractionFormViewModel
import com.google.gson.Gson

@Composable
fun InteractionFormRoot() {
    val vm = viewModel<InteractionFormViewModel>()
    val state by vm.interactionFlow.collectAsState(null)
    LaunchedEffect(state){
        vm.textValues.clear()
        vm.textValues.addAll(List(state?.fields?.size ?: 0){ "" })
    }

    Column(
        modifier = Modifier
            .background(
                when (state?.type) {
                    InteractionType.Insert -> Color(128, 180, 128)
                    InteractionType.Update -> Color(180, 180, 128)
                    InteractionType.Delete -> Color(180, 128, 128)
                    else -> Color.Gray
                }
            )
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(state?.name.toString(), fontSize = 30.sp)
        Divider(thickness = 2.dp)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.9f)
        )
        {
            item{
                TextField(
                    value = vm.baseUrl,
                    onValueChange = {vm.baseUrl = it},
                    label = {
                        Text("Base Url")
                    }
                )
            }
            itemsIndexed(state?.fields ?: listOf()){ ind, field ->
                TextField(
                    value = vm.textValues[ind],
                    onValueChange = {vm.textValues[ind] = it},
                    label = {
                        Text(field)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            modifier = Modifier
                .clip(CutCornerShape(10.dp))
                .fillMaxWidth(0.5f)
                .height(60.dp),
            onClick = {
                vm.executeFormSubmission(state!!)
            }
        )
        {
            Text(text = "Выполнить")
        }
    }
}