package com.example.bookly.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.bookly.R

val replyTypography = Typography(

    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.robotoslab_bold)),
        fontSize = 45.sp,
        lineHeight = 52.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        fontSize = 28.sp,
        lineHeight = 36.sp,
    ),
)