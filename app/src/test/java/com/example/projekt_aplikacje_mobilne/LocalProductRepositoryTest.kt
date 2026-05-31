package com.example.projekt_aplikacje_mobilne.data

import com.example.projekt_aplikacje_mobilne.model.ProductCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LocalProductRepositoryTest {

    private val repository = LocalProductRepository()

    @Test
    fun getFeaturedProducts_returnsOnlyFeaturedProducts() {
        val featured = repository.getFeaturedProducts()

        assertTrue(featured.isNotEmpty())
        assertTrue(featured.all { it.featured })
    }

    @Test
    fun getProductsByCategory_returnsOnlyProductsFromSelectedCategory() {
        val goggles = repository.getProductsByCategory(ProductCategory.GOGGLES)

        assertTrue(goggles.isNotEmpty())
        assertTrue(goggles.all { it.category == ProductCategory.GOGGLES })
    }

    @Test
    fun getProductById_returnsExpectedProduct() {
        val product = repository.getProductById("goggles_biofuse_2")

        assertNotNull(product)
        assertEquals("Speedo Biofuse 2.0 Goggles", product?.name)
    }
    @Test
    fun getProductById_returnsNullForUnknownId() {
        val product = repository.getProductById("nie_istnieje")
        assertEquals(null, product)
    }
}