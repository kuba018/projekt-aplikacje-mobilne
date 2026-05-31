package com.example.projekt_aplikacje_mobilne.model

data class ProductMedia(
    val id: String,
    val type: MediaType,
    val title: String,
    val drawableResId: Int? = null,
    val rawResId: Int? = null,
    val thumbnailResId: Int? = null,
    val description: String
)