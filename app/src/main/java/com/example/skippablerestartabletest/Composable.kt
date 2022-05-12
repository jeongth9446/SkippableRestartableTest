package com.example.skippablerestartabletest

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State

/** Skippable & Restartable */
@Composable
fun NoParameterCompose() {
    Log.d("Recompose", "NoParameterCompose")
    Text("Test")
}

/** Skippable & Restartable */
/** @param text is @dynamic String() */
@Composable
fun DefaultConstructorParameterCompose(text: String = String()) {
    Log.d("Recompose", "DefaultConstructorParameterCompose")
    Text(text)
}

/** Skippable & Restartable */
/** @param text is @static null */
@Composable
fun DefaultNullValueParameterCompose(text: String ?= null) {
    Log.d("Recompose", "DefaultNullValueParameterCompose")
    Text(text ?: "")
}

/** Skippable & Restartable */
/** @param text is @dynamic LiveLiterals$ComposableKt.String$param-text$fun-DefaultStaticValueParameterCompose() */
@Composable
fun DefaultStaticValueParameterCompose(text: String = "text") {
    Log.d("Recompose", "DefaultStaticValueParameterCompose")
    Text(text)
}

/** Restartable */
/** @param text is @dynamic arrayListOf(LiveLiterals$ComposableKt.String$0$vararg$arg-0$call-arrayListOf$param-text$fun-DefaultArrayListParameterCompose() */
/** It is skippable if param is not passed. (applied default value) */
/** It is not skippable if parm is passed. */
@Composable
fun DefaultArrayListParameterCompose(text: ArrayList<String> = arrayListOf("test")) {
    Log.d("Recompose", "DefaultArrayListParameterCompose")
    Text(text.first())
}

/** Skippable & Restartable*/
/** It is skippable when ArrayList<String> is not changed. */
@Composable
fun StateArrayListParameterCompose(text: State<ArrayList<String>>) {
    Log.d("Recompose", "StateArrayListParameterCompose")
    Text(text.value.first())
}

/** Skippable & Restartable*/
/** It is not skippable */
@Composable
fun UnstableDataClassParameterCompose(text: UnstableDataClass) {
    Log.d("Recompose", "UnstableDataClassParameterCompose")
    Text((text.a + text.b + text.c).toString())
}

/** Skippable & Restartable*/
/** It is skippable */
@Composable
fun StableDataClassParameterCompose(text: StableDataClass) {
    Log.d("Recompose", "StableDataClassParameterCompose")
    Text((text.a + text.b + text.c).toString())
}

/** Skippable & Restartable*/
/** It is skippable */
@Composable
fun AnnotatedDataClassParameterCompose(text: AnnotatedUnstableDataClass) {
    Log.d("Recompose", "AnnotatedDataClassParameterCompose")
    Text((text.a + text.b + text.c).toString())
}

data class UnstableDataClass(
    var a: Int,
    var b: Int,
    var c: Int
)

data class StableDataClass(
    val a: Int,
    val b: Int,
    val c: Int
)

@Stable
data class AnnotatedUnstableDataClass(
    var a: Int,
    var b: Int,
    var c: Int
)