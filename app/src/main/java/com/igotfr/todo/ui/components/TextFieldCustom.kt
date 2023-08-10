package com.igotfr.todo.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.tooling.preview.Preview
import com.igotfr.todo.ui.theme.LightBlue
import com.igotfr.todo.ui.theme.TextAreaShapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustom(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier,
  label: String,
  maxLines: Int,
  keyboardType: KeyboardType
) {
  OutlinedTextField(
    value,
    onValueChange,
    modifier,
    label = {
      Text(text = label)
    },
    maxLines = maxLines,
    colors = TextFieldDefaults.outlinedTextFieldColors(
      textColor = Color.Black,
      focusedBorderColor = LightBlue,
      focusedLabelColor = LightBlue,
      containerColor = Color.White,
      cursorColor = LightBlue
    ),
    shape = TextAreaShapes.small,
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType
    )
  )
}

@Preview
@Composable
private fun TextFieldCustomPreview() {
  TextFieldCustom(
    value = "Valor Teste",
    onValueChange = {},
    modifier = Modifier.fillMaxWidth(),
    label = "Label Teste",
    maxLines = 1,
    keyboardType = KeyboardType.Text
  )
}