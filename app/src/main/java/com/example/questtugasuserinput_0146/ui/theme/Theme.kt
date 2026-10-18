package com.example.questtugasuserinput_0146.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.questtugasuserinput_0146.R

@Composable
fun QuestTugasUserInput_0146Theme(
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme(
        primary = colorResource(id = R.color.theme_primary),
        secondary = colorResource(id = R.color.theme_secondary),
        tertiary = colorResource(id = R.color.theme_tertiary),
        background = colorResource(id = R.color.screen_background),
        surface = colorResource(id = R.color.card_background)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}