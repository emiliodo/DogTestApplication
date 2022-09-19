package com.babel.demo.dogapp.data.model

import android.os.Parcelable
import com.babel.demo.dogapp.data.utils.checkNull
import com.babel.demo.dogapp.domain.model.Dog
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogResponse(
    @SerializedName("breeds")
    val breeds: List<Breed>? = emptyList(),
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
) : Parcelable {
    fun toDomainModel(): Dog =
        Dog(
            id = id.checkNull(),
            name = breeds?.firstOrNull()?.name.checkNull(),
            origin = breeds?.firstOrNull()?.origin.checkNull(),
            breedGroup = breeds?.firstOrNull()?.breed_group.checkNull(),
            imageUrl = url.checkNull(),
            temperament = breeds?.firstOrNull()?.temperament.checkNull(),
            weight = breeds?.firstOrNull()?.weight?.imperial.checkNull(),
            height = breeds?.firstOrNull()?.height?.imperial.checkNull()
        )
}

@Parcelize
data class Breed(
    @SerializedName("bred_for")
    val bred_for: String?,
    @SerializedName("breed_group")
    val breed_group: String?,
    @SerializedName("country_code")
    val country_code: String?,
    @SerializedName("height")
    val height: Height?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("life_span")
    val life_span: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("weight")
    val weight: Weight?
) : Parcelable

@Parcelize
data class Height(
    @SerializedName("imperial")
    val imperial: String?,
    @SerializedName("metric")
    val metric: String?
) : Parcelable

@Parcelize
data class Weight(
    @SerializedName("imperial")
    val imperial: String?,
    @SerializedName("metric")
    val metric: String?
) : Parcelable