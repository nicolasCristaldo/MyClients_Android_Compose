package com.nicolascristaldo.myclients

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nicolascristaldo.myclients.ui.MyClientsApp
import com.nicolascristaldo.myclients.ui.theme.MyClientsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyClientsTheme {
                MyClientsApp()
            }
        }
    }
}