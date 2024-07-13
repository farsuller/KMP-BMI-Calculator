package calculator

import androidx.compose.ui.graphics.Color
import calculator.domain.BodyMeasurements
import calculator.domain.toBMI
import calculator.utils.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel : ViewModel(){
    
    private var _uiState = MutableStateFlow(UIState())
    val uiState : StateFlow<UIState>
        get() = _uiState
    
    
    init {
        updateUIState(_uiState.value.bodyMeasurements)
    }
    fun onEvent(event:UIEvent){
        when(event){
            is UIEvent.ChangeHeight -> {
                updateUIState(
                    _uiState.value.bodyMeasurements.copy(heightCm = event.heightCm)
                )
            }
            is UIEvent.ChangeWeight -> {
                updateUIState(
                    _uiState.value.bodyMeasurements.copy(weightKg = event.weightKg)
                )
            }
        }
    }
    
    private fun updateUIState(bodyMeasurements: BodyMeasurements){
        _uiState.update { state ->
            val bmi = bodyMeasurements.toBMI()
            state.copy(bodyMeasurements = bodyMeasurements,
                bmiDisplay = bmi.toFormattedString(),
                heightDisplay = bodyMeasurements.heightCm.roundUpToNearestInteger().toString(),
                weightDisplay = bodyMeasurements.weightKg.roundToNearestHalf().toString(),
                accentColor = bmi.toBMIColor(),
                bmiResultDisplay = bmi.toBMIResult()) }
    }
    
    sealed class UIEvent {
        data class ChangeHeight(val heightCm : Float) : UIEvent()
        data class ChangeWeight(val weightKg : Float) : UIEvent()
    }
    
    
    
    data class UIState(
        val bodyMeasurements: BodyMeasurements = BodyMeasurements(170f,60f),
        val bmiDisplay : String = "",
        val heightDisplay : String = "",
        val weightDisplay : String = "",
        val accentColor : Color = Color.Blue,
        val bmiResultDisplay : String = ""
    )
}