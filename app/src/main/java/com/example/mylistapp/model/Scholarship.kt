package com.example.mylistapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Scholarship(
    val name: String,
    val description: String,
    val onlinePhoto: String
) : Parcelable
