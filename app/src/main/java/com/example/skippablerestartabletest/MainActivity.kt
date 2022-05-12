package com.example.skippablerestartabletest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.skippablerestartabletest.ui.theme.SkippableRestartableTestTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateArrayListTest()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SkippableRestartableTestTheme {
        Greeting("Android")
    }
}

@Composable
fun StateArrayListTest() {
    var testVal by remember { mutableStateOf(0) }
    val rememberArrayList = remember { mutableStateOf(arrayListOf("test")) }
    val rememberUnstableDataClass = remember { mutableStateOf(UnstableDataClass(1, 2, 3)) }

    val stateArrayList = mutableStateOf(arrayListOf("test"))
    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            testVal += 1
            rememberArrayList.value.add("test $testVal") //observable 하지 않음
            stateArrayList.value = arrayListOf("test $testVal") //observable 함
        }

    }

    SkippableRestartableTestTheme {

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Greeting("Android")
            Column() {
                Log.d("Recompose", "textVal Updated : $testVal")
                NoParameterCompose() //recomposition 하지 않음.
                DefaultConstructorParameterCompose(String()) //recomposition 하지 않음.
                DefaultNullValueParameterCompose() //recomposition 하지 않음.
                DefaultStaticValueParameterCompose() //recomposition 하지 않음.
                DefaultArrayListParameterCompose() //recomposition 하지 않음.
                DefaultArrayListParameterCompose(rememberArrayList.value) //recomposition 하지 않음.
                StateArrayListParameterCompose(stateArrayList) //recomposition함
                UnstableDataClassParameterCompose(rememberUnstableDataClass.value) //항상 recomposition함
                StableDataClassParameterCompose(StableDataClass(1, 2, 3)) //smart recomposition
                AnnotatedDataClassParameterCompose(AnnotatedUnstableDataClass(testVal, 2, 3)) //smart recomposition
            }
        }
    }
}