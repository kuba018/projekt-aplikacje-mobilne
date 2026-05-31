package com.example.projekt_aplikacje_mobilne.model

data class Product(
    val id: String,
    val name: String,
    val category: ProductCategory,
    val shortDescription: String,
    val longDescription: String,
    val mainImageResId: Int,
    val galleryImageResIds: List<Int>,
    val videoResId: Int?,
    val videoThumbnailResId: Int?,
    val audioResId: Int?,
    val audioTitle: String?,
    val featured: Boolean = false
)