package com.babel.demo.dogapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dog(
    val id: String,
    val name: String,
    val imageUrl: String,
    val origin: String,
    val breedGroup: String,
    val temperament: String,
    val weight: String,
    val height: String
): Parcelable