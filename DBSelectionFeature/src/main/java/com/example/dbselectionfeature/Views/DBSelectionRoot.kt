package com.example.dbselectionfeature.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dbselectionfeature.R
import com.example.dbselectionfeature.ViewModels.DBSelectionViewModel
import com.google.gson.Gson

@Composable
fun DBSelectionRoot(
    onShowSelected: (String) -> Unit,
    onChangeSelected: (String) -> Unit
)
{
    val vm = viewModel<DBSelectionViewModel>()

    val focusManager = LocalFocusManager.current

    val tables by vm.tableNames.collectAsState(emptyList())
    Column(
        modifier = Modifier
            .background(Color.Gray)
            .pointerInput(1) {
                detectTapGestures {
                    focusManager.clearFocus(true)
                }
            }
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(Modifier.weight(2f))
        TextField(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            value = vm.currentTextFieldValue,
            label = {
                Text("Название таблицы")
            },
            trailingIcon = {
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { vm.updateTextFieldValue("") }
                        .padding(5.dp)
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.x),
                    contentDescription = null
                )
            },
            onValueChange = {
                vm.updateTextFieldValue(it)
            },
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = if (!tables.contains(vm.currentTextFieldValue) && vm.currentTextFieldValue.isNotEmpty()) Color.Red else Color(0, 100, 0),
                focusedTextColor = Color.Black
            ),
        )
        LazyColumn(
            modifier = Modifier
                .background(Color.LightGray)
                .weight(6f)
                .fillMaxWidth(0.7f)
        )
        {
            items(tables.filter { vm.currentTextFieldValue.takeIf{ it.isNotEmpty() }?.run{ this in it } ?: true }){
                Box(
                    modifier = Modifier
                        .clickable { vm.updateTextFieldValue(it) }
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = it,
                    )
                }
            }
        }
        Spacer(Modifier.weight(1f))
        Row(

        )
        {
            Button(
                onClick = { onShowSelected(Gson().toJson(vm.tables.value.first{ table -> table.name == vm.currentTextFieldValue })) },
                enabled = tables.contains(vm.currentTextFieldValue)
            )
            {
                Text("Показать данные")
            }
            Spacer(Modifier.width(40.dp))
            Button(
                onClick = { onChangeSelected(Gson().toJson(vm.tables.value.first{ table -> table.name == vm.currentTextFieldValue })) },
                enabled = tables.contains(vm.currentTextFieldValue)
            )
            {
                Text("Изменить данные")
            }
        }
        Spacer(Modifier.weight(2f))
    }
}