package com.miphorez.taskapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.miphorez.taskapplication.ui.ScreensByUiState
import com.miphorez.taskapplication.ui.theme.TaskApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskApplicationTheme {
                ScreensByUiState()
            }
        }
    }
}
