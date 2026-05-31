package com.example.projekt_aplikacje_mobilne.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductSeedConsistencyTest {

    @Test
    fun allProducts_haveUniqueIds() {
        val ids = ProductSeed.products.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun allProducts_haveNonEmptyNamesAndDescriptions() {
        assertTrue(
            ProductSeed.products.all {
                it.name.isNotBlank() &&
                        it.shortDescription.isNotBlank() &&
                        it.longDescription.isNotBlank()
            }
        )
    }

    @Test
    fun featuredProducts_existInSeed() {
        assertTrue(ProductSeed.products.any { it.featured })
    }
}