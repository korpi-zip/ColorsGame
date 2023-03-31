package com.example.colors

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


class MainActivity3 : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MyUI()
        }
    }
}

@Composable
fun ColorSection(proposedBackgroundColor: Color,
                 targetBackgroundColor :Color)
{

    Row(modifier = Modifier
        .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically


    ){
        Column(
            modifier = Modifier
                .padding(2.dp)
                .background(proposedBackgroundColor)
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.Proposed_color),
            )
        }
        Column (modifier = Modifier
            .padding(2.dp)
            .background(targetBackgroundColor)
            .fillMaxHeight()
            .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = stringResource(R.string.Target_color))
        }
    }


}


@Composable
fun SliderSection(title: String,
                  color:Color,
                  value:Float)
{
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = title,
                textAlign = TextAlign.Center)
        }
        Column (modifier = Modifier.weight(3f)){
            Slider(value = value,
                onValueChange = {},
                valueRange = 0f..255f,
                colors = SliderDefaults.colors(
                    thumbColor = color,
                    activeTickColor = color,
                    inactiveTickColor = Color.Gray
                ))
        }
        Column(modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = value.toString(),
                textAlign = TextAlign.Center)
        }

    }
}

@Composable
fun ButtonSection(

){
    Column(
        modifier = Modifier

    ) {
        Row{
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(text = stringResource(R.string.New))
            }
        }
        Row{
            Button(onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(text = stringResource(R.string.Score))
            }
        }
    }

}

@Composable
fun MyUI() {
    var proposedBackgroundColor by remember {mutableStateOf(Color.White)}
    var targetBackgroundColor by remember {mutableStateOf(Color.Red)}

    var redSliderValue by remember {mutableStateOf(128f)}
    var greenSliderValue by remember {mutableStateOf(128f)}
    var blueSliderValue by remember {mutableStateOf(128f)}

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(4.dp)
    ){
        Column (modifier = Modifier
            .fillMaxSize()
            .weight(2f))
        {
            ColorSection(proposedBackgroundColor,targetBackgroundColor)
        }
        SliderSection(title = stringResource(R.string.Red), color = Color.Red, value = redSliderValue)
        SliderSection(title = stringResource(R.string.Green), color = Color.Green, value = greenSliderValue)
        SliderSection(title = stringResource(R.string.Blue), color = Color.Blue, value = blueSliderValue)
        ButtonSection()

    }
}

@Preview(showBackground=true)
@Composable
fun DefaultPreview(){
    MyUI()
}