package com.example.projekt_aplikacje_mobilne.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projekt_aplikacje_mobilne.data.LocalProductRepository
import com.example.projekt_aplikacje_mobilne.favorites.FavoritesRepository
import com.example.projekt_aplikacje_mobilne.favorites.FavoritesViewModel
import com.example.projekt_aplikacje_mobilne.model.ProductCategory
import com.example.projekt_aplikacje_mobilne.settings.SettingsRepository
import com.example.projekt_aplikacje_mobilne.ui.categories.CategoriesScreen
import com.example.projekt_aplikacje_mobilne.ui.details.ProductDetailsScreen
import com.example.projekt_aplikacje_mobilne.ui.favorites.FavoritesScreen
import com.example.projekt_aplikacje_mobilne.ui.home.HomeScreen
import com.example.projekt_aplikacje_mobilne.ui.media.FullscreenImageScreen
import com.example.projekt_aplikacje_mobilne.ui.media.FullscreenVideoScreen
import com.example.projekt_aplikacje_mobilne.ui.products.CategoryProductsScreen
import com.example.projekt_aplikacje_mobilne.ui.settings.SettingsScreen

@Composable
fun AppNavGraph(
    productRepository: LocalProductRepository,
    settingsRepository: SettingsRepository,
    favoritesRepository: FavoritesRepository,
    favoritesViewModel: FavoritesViewModel
) {
    val navController = rememberNavController()
    val favoriteIds by favoritesViewModel.favoriteIds.collectAsState()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME
    ) {
        composable(AppDestinations.HOME) {
            HomeScreen(
                featuredProducts = productRepository.getFeaturedProducts(),
                favoriteIds = favoriteIds,
                onOpenCategories = { navController.navigate(AppDestinations.CATEGORIES) },
                onOpenFavorites = { navController.navigate(AppDestinations.FAVORITES) },
                onOpenSettings = { navController.navigate(AppDestinations.SETTINGS) },
                onProductClick = { productId ->
                    navController.navigate("${AppDestinations.PRODUCT_DETAILS}/$productId")
                },
                onFavoriteClick = favoritesViewModel::toggleFavorite
            )
        }

        composable(AppDestinations.CATEGORIES) {
            CategoriesScreen(
                categories = ProductCategory.entries,
                onBack = { navController.popBackStack() },
                onCategoryClick = { category ->
                    navController.navigate("${AppDestinations.CATEGORY_PRODUCTS}/${category.name}")
                },
                onOpenFavorites = { navController.navigate(AppDestinations.FAVORITES) },
                onOpenSettings = { navController.navigate(AppDestinations.SETTINGS) }
            )
        }

        composable(
            route = "${AppDestinations.CATEGORY_PRODUCTS}/{${NavArguments.CATEGORY}}",
            arguments = listOf(navArgument(NavArguments.CATEGORY) { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString(NavArguments.CATEGORY).orEmpty()
            val category = ProductCategory.valueOf(categoryName)

            CategoryProductsScreen(
                category = category,
                products = productRepository.getProductsByCategory(category),
                favoriteIds = favoriteIds,
                settingsRepository = settingsRepository,
                onBack = { navController.popBackStack() },
                onProductClick = { productId ->
                    navController.navigate("${AppDestinations.PRODUCT_DETAILS}/$productId")
                },
                onFavoriteClick = favoritesViewModel::toggleFavorite,
                onOpenFavorites = { navController.navigate(AppDestinations.FAVORITES) },
                onOpenSettings = { navController.navigate(AppDestinations.SETTINGS) }
            )
        }

        composable(
            route = "${AppDestinations.PRODUCT_DETAILS}/{${NavArguments.PRODUCT_ID}}",
            arguments = listOf(navArgument(NavArguments.PRODUCT_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(NavArguments.PRODUCT_ID).orEmpty()

            ProductDetailsScreen(
                productId = productId,
                productRepository = productRepository,
                favoritesRepository = favoritesRepository,
                onBack = { navController.popBackStack() },
                onOpenImage = { imageIndex ->
                    navController.navigate("${AppDestinations.FULLSCREEN_IMAGE}/$productId/$imageIndex")
                },
                onOpenVideo = {
                    navController.navigate("${AppDestinations.FULLSCREEN_VIDEO}/$productId")
                }
            )
        }

        composable(
            route = "${AppDestinations.FULLSCREEN_IMAGE}/{${NavArguments.PRODUCT_ID}}/{${NavArguments.IMAGE_INDEX}}",
            arguments = listOf(
                navArgument(NavArguments.PRODUCT_ID) { type = NavType.StringType },
                navArgument(NavArguments.IMAGE_INDEX) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(NavArguments.PRODUCT_ID).orEmpty()
            val imageIndex = backStackEntry.arguments?.getInt(NavArguments.IMAGE_INDEX) ?: 0

            FullscreenImageScreen(
                productId = productId,
                initialImageIndex = imageIndex,
                productRepository = productRepository,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "${AppDestinations.FULLSCREEN_VIDEO}/{${NavArguments.PRODUCT_ID}}",
            arguments = listOf(
                navArgument(NavArguments.PRODUCT_ID) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(NavArguments.PRODUCT_ID).orEmpty()

            FullscreenVideoScreen(
                productId = productId,
                productRepository = productRepository,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppDestinations.FAVORITES) {
            FavoritesScreen(
                products = productRepository.getAllProducts().filter { favoriteIds.contains(it.id) },
                favoriteIds = favoriteIds,
                settingsRepository = settingsRepository,
                onBack = { navController.popBackStack() },
                onProductClick = { productId ->
                    navController.navigate("${AppDestinations.PRODUCT_DETAILS}/$productId")
                },
                onFavoriteClick = favoritesViewModel::toggleFavorite
            )
        }

        composable(AppDestinations.SETTINGS) {
            SettingsScreen(
                settingsRepository = settingsRepository,
                onBack = { navController.popBackStack() }
            )
        }
    }
}