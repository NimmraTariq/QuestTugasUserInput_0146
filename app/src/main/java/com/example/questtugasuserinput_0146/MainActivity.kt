package com.example.questtugasuserinput_0146

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.questtugasuserinput_0146.ui.theme.QuestTugasUserInput_0146Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestTugasUserInput_0146Theme {
                BiodataScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BiodataPreview() {
    QuestTugasUserInput_0146Theme {
        BiodataScreen()
    }
}