package com.example.myapplication.data.presentation.compose


import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.domain.Settings
import com.example.myapplication.data.domain.model.Setting


@Composable
@ColorRes
fun AboutAppItem(setting: Setting) {

    Text(
        text = stringResource(id = setting.title),
        style = TextStyle(
            fontSize = 19.sp,
            color = colorResource(id = R.color.white)
        )
    )
    Text(
        text = stringResource(id = setting.subtitle),
        style = TextStyle(
            fontSize = 19.sp,
            color = colorResource(id = R.color.silver)
        )
    )
}




@Composable
@Preview
fun DefaultApp(){

    AboutAppItem(setting = Settings.settings[0])

}