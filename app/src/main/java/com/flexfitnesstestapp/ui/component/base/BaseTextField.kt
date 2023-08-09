package com.flexfitnesstestapp.ui.component.base

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.ui.theme.nunitoFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    @StringRes placeholderRes: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    var underLineColor by remember { mutableStateOf(R.color.common_light_blue_grey) }
    var isFocused by remember {
        mutableStateOf(false)
    }
    underLineColor = if (!isError) {
        if (isFocused) R.color.common_text_field_ok else R.color.common_light_blue_grey
    } else {
        R.color.common_text_field_error
    }
//    val isFocused = interactionSource.collectIsFocusedAsState().value
//    Log.d("test", isFocused.toString())
//    val underLineColor =


    Card(
        modifier = modifier,
        shape = RoundedCornerShape(30),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
//        TextField(value = value, onValueChange = onValueChange, modifier = Modifier.onFocusChanged {
//            if (!isError) {
//                underLineColor =
//                    if (it.isFocused) R.color.common_text_field_ok else R.color.common_light_blue_grey
//            }
//            Log.d("test", it.isFocused.toString())
//        },
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = Color.Transparent,
//                disabledIndicatorColor = Color.Transparent,
//                errorIndicatorColor = Color.Transparent,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            ),
//            textStyle = TextStyle(
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Medium,
//                color = Color.Black,
//                lineHeight = 26.sp
//            ),
//            placeholder = {
//                placeholderRes?.let {
//                    Text(
//                        text = stringResource(id = it),
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = nunitoFamily,
//                        color = colorResource(id = R.color.common_light_blue_grey),
//                        fontSize = 22.sp,
//                        letterSpacing = 0.sp
//                    )
//                }
//
//            }
//        )
//        Divider(
//            modifier = Modifier
//                .padding(bottom = 2.dp),
//            thickness = 1.dp,
//            color = colorResource(id = underLineColor)
//        )
//    }


        BasicTextField(
            modifier = Modifier
                .onFocusChanged {
                    isFocused = it.isFocused
//                    if (!isError) {
//                        underLineColor =
//                            if (it.isFocused) R.color.common_text_field_ok else R.color.common_light_blue_grey
//                    }
                    Log.d("test", it.isFocused.toString())
                },
            value = value,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            cursorBrush = SolidColor(colorResource(id = R.color.common_text_cursor_ok)),
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                lineHeight = 26.sp
            ),

            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.padding(15.dp)
//                            .padding(horizontal = 64.dp) // margin left and right
//                            .fillMaxWidth()
//                            .border(
//                                width = 2.dp,
//                                color = Color(0xFFAAE9E6),
//                                shape = RoundedCornerShape(size = 16.dp)
//                            )
//                            .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                ) {
                    if (value.isEmpty() && placeholderRes != null) {
                        Text(
                            text = stringResource(id = placeholderRes),
                            fontWeight = FontWeight.Bold,
                            fontFamily = nunitoFamily,
                            color = colorResource(id = R.color.common_light_blue_grey),
                            fontSize = 22.sp,
                            letterSpacing = 0.sp,
                            lineHeight = 26.sp
                        )
                    }
                    innerTextField()
                    Divider(
                        modifier = Modifier
//                            .padding(bottom = 2.dp)
                            .align(Alignment.BottomCenter),
                        thickness = 1.dp,
                        color = colorResource(id = underLineColor)
                    )
                }
            }
        )
//        TextField(
//            value = value, onValueChange = onValueChange,
//            placeholder = {
//                Text(
//                    text = if (placeholderRes != null) stringResource(id = placeholderRes) else "",
//                    fontWeight = FontWeight.Medium,
//                    color = colorResource(id = R.color.common_light_grey),
//                    fontSize = 20.sp,
//                    letterSpacing = 0.5.sp
//                )
//            },
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = Color.Transparent
//            ),
//            singleLine = true,
//
//            )
    }
}

