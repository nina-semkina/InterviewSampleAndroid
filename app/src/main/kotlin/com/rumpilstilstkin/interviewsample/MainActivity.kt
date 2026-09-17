package com.rumpilstilstkin.interviewsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rumpilstilstkin.interviewsample.ui.home.HomeScreen
import com.rumpilstilstkin.interviewsample.ui.theme.InterviewSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewSampleTheme {
                HomeScreen()
            }
        }
    }
}
