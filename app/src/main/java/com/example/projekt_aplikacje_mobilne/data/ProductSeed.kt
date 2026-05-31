package com.example.projekt_aplikacje_mobilne.data

import com.example.projekt_aplikacje_mobilne.R
import com.example.projekt_aplikacje_mobilne.model.Product
import com.example.projekt_aplikacje_mobilne.model.ProductCategory

object ProductSeed {

    val products: List<Product> = listOf(
        Product(
            id = "goggles_biofuse_2",
            name = "Speedo Biofuse 2.0 Goggles",
            category = ProductCategory.GOGGLES,
            shortDescription = ProductDescriptions.SPEEDO_BIOFUSE_SHORT,
            longDescription = ProductDescriptions.SPEEDO_BIOFUSE_LONG,
            mainImageResId = R.drawable.biofusegog1,
            galleryImageResIds = ResourceRegistry.biofuseGogglesGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null,
            featured = true
        ),
        Product(
            id = "goggles_arena_tracks",
            name = "Arena Tracks",
            category = ProductCategory.GOGGLES,
            shortDescription = ProductDescriptions.ARENA_TRACKS_SHORT,
            longDescription = ProductDescriptions.ARENA_TRACKS_LONG,
            mainImageResId = R.drawable.arenagog1,
            galleryImageResIds = ResourceRegistry.arenaTracksGallery,
            videoResId = R.raw.wideo,
            videoThumbnailResId = R.drawable.grafikadowideo,
            audioResId = null,
            audioTitle = null,
            featured = true
        ),
        Product(
            id = "goggles_blackhawk_racing",
            name = "TYR Blackhawk Racing Goggles",
            category = ProductCategory.GOGGLES,
            shortDescription = ProductDescriptions.TYR_BLACKHAWK_SHORT,
            longDescription = ProductDescriptions.TYR_BLACKHAWK_LONG,
            mainImageResId = R.drawable.blackhawkgog1,
            galleryImageResIds = ResourceRegistry.blackhawkGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null
        ),
        Product(
            id = "goggles_fastskin_hyper_elite",
            name = "Speedo Fastskin Hyper Elite Mirror Goggles",
            category = ProductCategory.GOGGLES,
            shortDescription = ProductDescriptions.FASTSKIN_SHORT,
            longDescription = ProductDescriptions.FASTSKIN_LONG,
            mainImageResId = R.drawable.fastskingog1,
            galleryImageResIds = ResourceRegistry.fastskinGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null
        ),
        Product(
            id = "fins_long_floating_fins",
            name = "FINIS Long Floating Fins",
            category = ProductCategory.FINS,
            shortDescription = ProductDescriptions.FINIS_LONG_FLOATING_SHORT,
            longDescription = ProductDescriptions.FINIS_LONG_FLOATING_LONG,
            mainImageResId = R.drawable.finis1,
            galleryImageResIds = ResourceRegistry.finisGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null,
            featured = true
        ),
        Product(
            id = "fins_powerfin_pro_2",
            name = "Arena Powerfin Pro II",
            category = ProductCategory.FINS,
            shortDescription = ProductDescriptions.POWERFIN_SHORT,
            longDescription = ProductDescriptions.POWERFIN_LONG,
            mainImageResId = R.drawable.powerfin1,
            galleryImageResIds = ResourceRegistry.powerfinGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null
        ),
        Product(
            id = "fins_biofuse_fitness_fins",
            name = "Speedo Biofuse Fitness Fins",
            category = ProductCategory.FINS,
            shortDescription = ProductDescriptions.BIOFUSE_FINS_SHORT,
            longDescription = ProductDescriptions.BIOFUSE_FINS_LONG,
            mainImageResId = R.drawable.speedofins1,
            galleryImageResIds = ResourceRegistry.biofuseFitnessFinsGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null
        ),
        Product(
            id = "fins_stryker_silicone_fins",
            name = "TYR Stryker Silicone Fins",
            category = ProductCategory.FINS,
            shortDescription = ProductDescriptions.STRYKER_SHORT,
            longDescription = ProductDescriptions.STRYKER_LONG,
            mainImageResId = R.drawable.tyrfins1,
            galleryImageResIds = ResourceRegistry.tyrStrykerGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null
        ),
        Product(
            id = "caps_smartcap",
            name = "Arena SmartCap",
            category = ProductCategory.CAPS,
            shortDescription = ProductDescriptions.SMARTCAP_SHORT,
            longDescription = ProductDescriptions.SMARTCAP_LONG,
            mainImageResId = R.drawable.arenasmartcap1,
            galleryImageResIds = ResourceRegistry.arenaSmartCapGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null
        ),
        Product(
            id = "caps_long_hair_cap",
            name = "Speedo Silicone Long Hair Cap",
            category = ProductCategory.CAPS,
            shortDescription = ProductDescriptions.LONG_HAIR_SHORT,
            longDescription = ProductDescriptions.LONG_HAIR_LONG,
            mainImageResId = R.drawable.speedocap,
            galleryImageResIds = ResourceRegistry.speedoLongHairCapGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null
        ),
        Product(
            id = "caps_classic_silicone_cap",
            name = "Arena Classic Silicone Swim Cap",
            category = ProductCategory.CAPS,
            shortDescription = ProductDescriptions.CLASSIC_CAP_SHORT,
            longDescription = ProductDescriptions.CLASSIC_CAP_LONG,
            mainImageResId = R.drawable.arenaclassiccap1,
            galleryImageResIds = ResourceRegistry.arenaClassicCapGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null
        ),
        Product(
            id = "caps_wrinkle_free_cap",
            name = "TYR Adult Silicone Wrinkle-Free Swim Cap",
            category = ProductCategory.CAPS,
            shortDescription = ProductDescriptions.WRINKLE_FREE_SHORT,
            longDescription = ProductDescriptions.WRINKLE_FREE_LONG,
            mainImageResId = R.drawable.tyrcap1,
            galleryImageResIds = ResourceRegistry.tyrWrinkleFreeCapGallery,
            videoResId = null,
            videoThumbnailResId = null,
            audioResId = null,
            audioTitle = null
        )
    )
}