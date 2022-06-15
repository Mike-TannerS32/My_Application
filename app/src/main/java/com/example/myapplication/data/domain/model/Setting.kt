package com.example.myapplication.data.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.myapplication.INVALID_ID

data class Setting(
    val type: SettingType,
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val subtitle: Int = INVALID_ID,
    val website: String?= null
){
    enum class SettingType{
        APP, HEADER, BODY
    }
}