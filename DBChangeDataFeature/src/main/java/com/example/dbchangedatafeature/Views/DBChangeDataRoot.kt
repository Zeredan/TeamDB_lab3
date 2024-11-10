package com.example.dbchangedatafeature.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.TableInteractionInfo.InteractionType
import com.example.dbchangedatafeature.R
import com.example.dbchangedatafeature.ViewModels.DBChangeDataViewModel
import com.google.gson.Gson

@Composable
fun DBChangeDataRoot(
    onInteractionSelected: (String) -> Unit
)
{
    val vm = viewModel<DBChangeDataViewModel>()
    val table by vm.selectedTable.collectAsState(null)

    Column(
        modifier = Modifier
            .background(Color.Gray)
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(table?.name.toString(), fontSize = 30.sp)
        Divider(thickness = 2.dp)
        Spacer(modifier = Modifier.height(20.dp))
        InteractionsGrid(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.8f)
                .background(Color(128, 180, 128)),
            elements = table?.interactions?.filter { it.type == InteractionType.Insert }?.map { it.name } ?: listOf(),
            colorResource(id = R.color.green_spec),
            colorResource(id = R.color.dark_green)
        )
        {
            onInteractionSelected(Gson().toJson(table!!.interactions.first{ it1 -> it1.name == it}))
        }
        Spacer(modifier = Modifier.height(20.dp))
        InteractionsGrid(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.8f)
                .background(Color(180, 180, 128)),
            elements = table?.interactions?.filter { it.type == InteractionType.Update }?.map { it.name } ?: listOf(),
            colorResource(id = R.color.yellow_spec),
            colorResource(id = R.color.ochra)
        )
        {
            onInteractionSelected(Gson().toJson(table!!.interactions.first{ it1 -> it1.name == it}))
        }
        Spacer(modifier = Modifier.height(20.dp))
        InteractionsGrid(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.8f)
                .background(Color(180, 128, 128)),
            elements = table?.interactions?.filter { it.type == InteractionType.Delete }?.map { it.name } ?: listOf(),
            colorResource(id = R.color.red_spec),
            colorResource(id = R.color.bloody)
        )
        {
            onInteractionSelected(Gson().toJson(table!!.interactions.first{ it1 -> it1.name == it}))
        }
    }
}