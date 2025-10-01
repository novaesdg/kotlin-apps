/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.reply.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.reply.ui.theme.replyLightPrimary

private val replyLightColorScheme = lightColorScheme(
    primary = Brown,
    onPrimary = White,
    primaryContainer = BeigeLight,
    onPrimaryContainer = TextBlack,
    secondary = BeigeMedium,
    onSecondary = TextBlack,
    secondaryContainer = WhiteBluish,
    onSecondaryContainer = TextBlack,
    tertiary = BeigeLight,
    onTertiary = TextBlack,
    tertiaryContainer = White,
    onTertiaryContainer = TextBlack,
    error = replyDarkError,
    errorContainer = replyDarkOnError,
    onError = replyDarkErrorContainer,
    onErrorContainer = replyDarkOnErrorContainer,
    background = WhiteBluish,
    onBackground = TextBlack,
    surface = WhiteBluish,
    onSurface = TextBlack,
    surfaceVariant = WhiteBluish,
    onSurfaceVariant = Brown,
    outline = BeigeMedium,
    inverseOnSurface = WhiteBluish,
    inverseSurface = TextBlack,
    inversePrimary = BeigeLight,
)

private val replyDarkColorScheme = darkColorScheme(
    primary = Brown,
    onPrimary = White,
    primaryContainer = BeigeLight,
    onPrimaryContainer = TextBlack,
    secondary = BeigeMedium,
    onSecondary = TextBlack,
    secondaryContainer = Brown,
    onSecondaryContainer = White,
    tertiary = BeigeLight,
    onTertiary = TextBlack,
    tertiaryContainer = Brown,
    onTertiaryContainer = White,
    error = replyDarkError,
    errorContainer = replyDarkOnError,
    onError = replyDarkErrorContainer,
    onErrorContainer = replyDarkOnErrorContainer,
    background = TextBlack,
    onBackground = White,
    surface = TextBlack,
    onSurface = White,
    surfaceVariant = TextBlack,
    onSurfaceVariant = Brown,
    outline = BeigeMedium,
    inverseOnSurface = TextBlack,
    inverseSurface = White,
    inversePrimary = BeigeMedium,
)

@Composable
fun ReplyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val replyColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> replyDarkColorScheme
        else -> replyLightColorScheme
    }

    MaterialTheme(
        colorScheme = replyColorScheme,
        typography = replyTypography,
        content = content
    )
}