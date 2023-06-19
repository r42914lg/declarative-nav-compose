package com.r42914lg.declarativenavcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.r42914lg.declarativenavcompose.ui.theme.DeclarativeNavComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeclarativeNavComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

@Composable
fun ScreenA(
    onClick: (Int, String) -> Unit,
    onClickBack: () -> Unit,
) {
    BackHandler {
        onClickBack()
    }
    Text(
        text = "Click to B Screen",
        modifier = Modifier.clickable {
            onClick(99, "buda-bum")
        }
    )
}

@Composable
fun ScreenB(
    onClick: () -> Unit,
    onClickBack: () -> Unit,
    arg1: Int,
    arg2: String,
) {
    BackHandler {
        onClickBack()
    }
    Column{
        Text(
            text = "Mandatory arg is $arg1, optional - $arg2",
            modifier = Modifier.clickable {
                onClick()
            }
        )
        Text(
            text = "Click to C Screen",
            modifier = Modifier.clickable {
                onClick()
            }
        )
    }
}

@Composable
fun ScreenC(
    onClick: () -> Unit,
    onClickBack: () -> Unit,
) {
    BackHandler {
        onClickBack()
    }
    Text(
        text = "Click to A Screen",
        modifier = Modifier.clickable {
            onClick()
        }
    )
}