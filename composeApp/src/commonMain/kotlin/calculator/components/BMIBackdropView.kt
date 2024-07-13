package calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BMIBackdropView(modifier : Modifier = Modifier, color: Color, bmiResult: String){
    Box(modifier = modifier.background(color),
        contentAlignment = Alignment.Center){
        Text(text = bmiResult, color = Color.White, fontSize = 48.sp)
    }
    
}

@Preview
@Composable
private fun BMIBackdropPreview(){
    BMIBackdropView(modifier = Modifier.fillMaxSize(), Color.Red, "Normal")
}