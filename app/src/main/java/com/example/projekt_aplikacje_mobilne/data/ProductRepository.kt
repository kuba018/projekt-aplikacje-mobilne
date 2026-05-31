package com.example.projekt_aplikacje_mobilne.data

import com.example.projekt_aplikacje_mobilne.model.Product
import com.example.projekt_aplikacje_mobilne.model.ProductCategory

interface ProductRepository {
    fun getAllProducts(): List<Product>
    fun getProductsByCategory(category: ProductCategory): List<Product>
    fun getProductById(productId: String): Product?
    fun getFeaturedProducts(): List<Product>
}