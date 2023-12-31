package com.igotfr.todo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igotfr.todo.ui.theme.LightBlue

@Composable
fun ButtonCustom(
  onClick: () -> Unit,
  modifier: Modifier,
  text: String
) {
  Button(
    onClick,
    modifier,
    colors = ButtonDefaults.buttonColors(
      containerColor = LightBlue,
      contentColor = Color.White
    )
  ) {
    Text(
      text,
      fontWeight = FontWeight.Bold,
      fontSize = 18.sp
    )
  }
}

@Preview
@Composable
private fun ButtonCustomPreview() {
  ButtonCustom(
    onClick = {},
    modifier = Modifier
      .fillMaxWidth()
      .height(90.dp)
      .padding(20.dp),
    text = "Preview"
  )
}