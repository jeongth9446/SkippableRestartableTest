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
            //rememberArrayList.value.add("test $testVal")
            stateArrayList.value = arrayListOf("test $testVal")
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
                NoParameterCompose()
                DefaultConstructorParameterCompose(String())
                DefaultNullValueParameterCompose()
                DefaultStaticValueParameterCompose()
                DefaultArrayListParameterCompose()
                DefaultArrayListParameterCompose(rememberArrayList.value)
                StateArrayListParameterCompose(stateArrayList)
                UnstableDataClassParameterCompose(rememberUnstableDataClass.value)
                StableDataClassParameterCompose(StableDataClass(1, 2, 3))
                AnnotatedDataClassParameterCompose(AnnotatedUnstableDataClass(testVal, 2, 3))
            }
        }
    }
}