package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = RegalGold,
    onPrimary = ObsidianDark,
    secondary = GoldMuted,
    onSecondary = OffWhite,
    tertiary = ImperialGold,
    background = ObsidianDark,
    onBackground = OffWhite,
    surface = CardBackground,
    onSurface = OffWhite,
    surfaceVariant = CharcoalGray,
    onSurfaceVariant = OffWhite
  )

private val LightColorScheme =
  darkColorScheme( // In luxury, a dark aesthetic is universally maintained
    primary = RegalGold,
    onPrimary = ObsidianDark,
    secondary = GoldMuted,
    onSecondary = OffWhite,
    tertiary = ImperialGold,
    background = ObsidianDark,
    onBackground = OffWhite,
    surface = CardBackground,
    onSurface = OffWhite,
    surfaceVariant = CharcoalGray,
    onSurfaceVariant = OffWhite
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true, // Force premium dark layout for Joven Millonario
  dynamicColor: Boolean = false, // Preserve high-contrast custom gold colors
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
