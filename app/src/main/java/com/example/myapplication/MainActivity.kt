package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.ui.ProfileContainer
import com.example.myapplication.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ProfilDeveloppeurTheme {
                ProfileContainer(
                    onContactClick = {
                    },
                )
            }
        }
    }
}