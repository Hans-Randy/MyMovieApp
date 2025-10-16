package com.example.mymovieapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// App color palette — tuned for a clean, modern movie vibe
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF60A5FA),          // Blue-400
    onPrimary = Color(0xFF0B1220),
    primaryContainer = Color(0xFF1F2A44),
    onPrimaryContainer = Color(0xFFE6F0FF),

    secondary = Color(0xFF34D399),        // Emerald-400
    onSecondary = Color(0xFF062016),
    secondaryContainer = Color(0xFF123D2C),
    onSecondaryContainer = Color(0xFFE9FFF6),

    tertiary = Color(0xFFF59E0B),         // Amber-500
    onTertiary = Color(0xFF221700),
    tertiaryContainer = Color(0xFF3D2B00),
    onTertiaryContainer = Color(0xFFFFF3E0),

    background = Color(0xFF0B1220),       // Near-navy dark
    onBackground = Color(0xFFE6ECF7),
    surface = Color(0xFF111A2E),
    onSurface = Color(0xFFE2E8F0),
    surfaceVariant = Color(0xFF1A2542),
    onSurfaceVariant = Color(0xFFB8C4D9),

    error = Color(0xFFF87171),
    onError = Color(0xFF3B0A0A)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF3B82F6),          // Blue-500
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFDCEBFF),
    onPrimaryContainer = Color(0xFF0B1220),

    secondary = Color(0xFF22C55E),        // Emerald-500
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDDF7EA),
    onSecondaryContainer = Color(0xFF062016),

    tertiary = Color(0xFFF59E0B),         // Amber-500
    onTertiary = Color(0xFF1F1500),
    tertiaryContainer = Color(0xFFFFF3C4),
    onTertiaryContainer = Color(0xFF221700),

    background = Color(0xFFF7FAFF),       // Subtle blue-tinted white
    onBackground = Color(0xFF0B1220),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFE6ECF7),
    onSurfaceVariant = Color(0xFF334155),

    error = Color(0xFFDC2626),
    onError = Color(0xFFFFFFFF)
)

private val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 42.sp,
        lineHeight = 48.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.15.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp
    )
)

@Composable
fun MyMovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
