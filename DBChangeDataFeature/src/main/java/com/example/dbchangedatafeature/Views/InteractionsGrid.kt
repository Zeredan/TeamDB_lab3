package com.example.dbchangedatafeature.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.unit.dp

@Composable
fun <T> InteractionsGrid(modifier: Modifier, elements: List<T>, bgMid: Color, bgSide: Color, onItemClicked: (T) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier
            .border(color = bgMid, width = 2.dp, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .then(modifier),
        columns = GridCells.Fixed(2)
    )
    {
        items(elements){
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .clip(CircleShape)
                    .clickable { onItemClicked(it) }
                    .background(object: ShaderBrush(){
                        override fun createShader(size: Size): Shader {
                            return RadialGradientShader(
                                size.center,
                                size.width * 0.5f,
                                listOf(bgMid, bgSide),
                                listOf(0f, 1f)
                            )
                        }
                    })
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            )
            {
                Text(text = it.toString())
            }
        }
    }
}