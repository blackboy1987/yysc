package com.bootx.yysc.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ButtonState {
    Normal,
    Pressed
}

@Composable
fun AnimatedButton(username: String, password: String,onLogin:()->Unit) {
    val buttonState = remember {
        mutableStateOf(ButtonState.Normal)
    }

    val color1 = Color(0xFF9ea5b8)
    val color3 = Color(0xFF211e6d)

    val transition = updateTransition(targetState = buttonState, label = "ButtonTransition")


    val duration = 600

    val buttonBackgroundColor: Color by transition.animateColor(
        transitionSpec = { tween(duration) },
        label = "Button Background Color"
    ) {
        buttonState->
        when(buttonState.value){
            ButtonState.Normal -> color3
            ButtonState.Pressed->color1
        }
    }

    val buttonWith: Dp by transition.animateDp(
        transitionSpec = { tween(duration) },
        label = "Button Width"
    ) {
            buttonState->
        when(buttonState.value){
            ButtonState.Normal -> 280.dp
            ButtonState.Pressed->60.dp
        }
    }

    val buttonShape: Dp by transition.animateDp(
        transitionSpec = { tween(duration) },
        label = "Button Shape"
    ) {
            buttonState->
        when(buttonState.value){
            ButtonState.Normal -> 4.dp
            ButtonState.Pressed->100.dp
        }
    }

    Button(
        modifier = Modifier
            .width(buttonWith)
            .height(48.dp),
        shape = RoundedCornerShape(buttonShape),
        enabled = username.isNotBlank() && password.isNotBlank(),
        onClick = {
            buttonState.value = if(buttonState.value == ButtonState.Normal){
                ButtonState.Pressed
            }else{
                ButtonState.Normal
            }
            onLogin()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonBackgroundColor,
            disabledContainerColor = color3.copy(0.5f),
        )
    ) {
        if(buttonState.value == ButtonState.Normal){
            Text(text = "登录", color = Color.White)
        }else{
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 2.dp,
                modifier = Modifier.size(24.dp,24.dp)
            )
        }
    }
}