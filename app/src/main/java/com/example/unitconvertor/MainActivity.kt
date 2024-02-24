package com.example.unitconvertor

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var OutputValue by remember {mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Meters")}
    var OutputUnit by remember {mutableStateOf("Meters")}
    var IExpanded by   remember {mutableStateOf(false)}
     var oExpanded by  remember {mutableStateOf(false)}
    val conversionFactor = remember {
        mutableStateOf(1.00)
    }
    val oconversionFactor = remember {
        mutableStateOf(1.00)
    }

val customTextStyle= androidx.compose.ui.text.TextStyle(
    fontFamily = FontFamily.Monospace,
    fontSize = 26.sp,
    color = Color.Red
)
    
fun convertUnits(){
    //?: - elvis operator
  val inputValueDouble= inputValue.toDoubleOrNull()?:0.0
    var  result =(inputValueDouble * conversionFactor.value *100.0/oconversionFactor.value).roundToInt()/100.0
    OutputValue =result.toString()
}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Unit Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange ={
           inputValue=it
            convertUnits()
        } , label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input box
           Box {
               //input button
             Button(onClick = {IExpanded=true}) {
                Text(text = inputUnit)
           Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
             }
               DropdownMenu(expanded = IExpanded, onDismissRequest = { IExpanded=false }) {
                   DropdownMenuItem(text = { Text(text = "Centimeters")},

                       onClick = {
                           oExpanded=false
                           OutputUnit="Centimeters"
                           conversionFactor.value =0.01
                           convertUnits()
                       })
                   DropdownMenuItem(text = { Text(text = "Meters")},
                       onClick = { oExpanded=false
                           OutputUnit="Meters"
                           conversionFactor.value =1.0
                           convertUnits()})
                   DropdownMenuItem(text = { Text(text = "Feet")},
                       onClick = { oExpanded=false
                           OutputUnit="Feet"
                           conversionFactor.value =0.348
                           convertUnits()} )
                   DropdownMenuItem(text = { Text(text = "Millimeter")},
                       onClick = { oExpanded=false
                           OutputUnit="Millimeter"
                           conversionFactor.value =0.001
                           convertUnits() })
                   
               }
           }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                //output box
                Button(onClick = { oExpanded=true }) {
                    Text(text = OutputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")},
                        onClick = {
                            oExpanded=false
                            OutputUnit="Centimeters"
                            oconversionFactor.value =0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Meters")},
                        onClick = { oExpanded=false
                            OutputUnit="Meters"
                            oconversionFactor.value =1.00
                            convertUnits() })
                    DropdownMenuItem(text = { Text(text = "Feet")},
                        onClick = { oExpanded=false
                            OutputUnit="Feet"
                            oconversionFactor.value =0.3048
                            convertUnits() })
                    DropdownMenuItem(text = { Text(text = "Millimeter")},
                        onClick = { oExpanded=false
                            OutputUnit="Millimeter"
                            oconversionFactor.value =0.001
                            convertUnits() })

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result:$OutputValue $OutputUnit" , style = MaterialTheme.typography.headlineMedium)


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){

    UnitConverter()
}