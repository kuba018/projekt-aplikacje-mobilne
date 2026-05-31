package com.example.projekt_aplikacje_mobilne.data

import com.example.projekt_aplikacje_mobilne.model.Product
import com.example.projekt_aplikacje_mobilne.model.ProductCategory

class LocalProductRepository : ProductRepository {
    override fun getAllProducts(): List<Product> = ProductSeed.products

    override fun getProductsByCategory(category: ProductCategory): List<Product> =
        ProductSeed.products.filter { it.category == category }

    override fun getProductById(productId: String): Product? =
        ProductSeed.products.find { it.id == productId }

    override fun getFeaturedProducts(): List<Product> =
        ProductSeed.products.filter { it.featured }
}