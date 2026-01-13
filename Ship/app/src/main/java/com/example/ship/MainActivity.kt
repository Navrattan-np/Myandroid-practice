package com.example.ship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.ship.ui.theme.ShipTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShipTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CaptainGame()
                }
            }
        }
    }

    @Composable
    fun CaptainGame(){
        val treasure =remember {mutableIntStateOf(0)}
        val direction=remember{mutableStateOf("North")}
        val hp=remember {mutableIntStateOf(100)}
        val lastEvent=remember{mutableStateOf("Ready to set sail, Captain!")}
        val logColor=remember{mutableStateOf(Color.Unspecified)}

        Column(
            modifier=Modifier.fillMaxSize()
        ){
            Text("Treasure found: ${treasure.intValue}")
            Text("Ship health(HP): ${hp.intValue}",color=if(hp.intValue<30) Color.Red else Color.Unspecified)
            Text("Current direction: ${direction.value}")
            Text("Log: ${lastEvent.value}",color=logColor.value)

            if(hp.intValue<=0){
                Text("Game over,your ship has sunk!",color=Color.Red)
                Button(onClick={
                    hp.intValue=100
                    lastEvent.value="New ship, new journey!"
                    treasure.intValue=0
                }){
                    Text("Restart game")
                }
            }
            else{
                val directions=listOf("East","West","North","South")
                val icons=listOf(Icons.Default.KeyboardArrowRight,Icons.Default.KeyboardArrowLeft,Icons.Default.KeyboardArrowUp,Icons.Default.KeyboardArrowDown)

                directions.forEachIndexed{ index,dir->
                    Button(onClick={
                        direction.value=dir

                        val rand= Random.nextInt(3)
                        when(rand){
                            0-> {
                                lastEvent.value = "Calm seas to the $dir ,no treasure found"
                                logColor.value=Color.Gray
                            }
                            1->{
                                treasure.intValue+=1
                                lastEvent.value="Success! ,you found the treasure in $dir direction"
                                logColor.value=Color.Green
                            }
                            2->{
                                hp.intValue-=10
                                lastEvent.value="Storm! the ship took damage sailing $dir"
                                logColor.value=Color.Red
                            }
                        }
                    }){
                        Text("Sail $dir!")
                        Icon(icons[index],contentDescription=null)
                    }

                }
            }


        }
    }

}

@Preview(showBackground=true)
@Composable
fun CaptainGamePreview(){
    val obj=MainActivity()
    obj.CaptainGame()
}

