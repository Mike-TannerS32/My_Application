package com.example.myapplication.data.presentation.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.data.domain.Settings
import com.example.myapplication.data.domain.model.Setting


@Composable
fun AboutAppItem(setting: Setting){

    Text(
        text = stringResource(id = setting.title )
    )
}


@Composable
@Preview
fun DefaultApp(){
    AboutAppItem(setting = Settings.settings[0])
}