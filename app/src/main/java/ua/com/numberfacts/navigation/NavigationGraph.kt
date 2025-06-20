package ua.com.numberfacts.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ua.com.numberfacts.R
import ua.com.numberfacts.presentation.home.HomeRoot
import kotlin.reflect.KClass


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NavigationRoot(
    navController: NavHostController,
    innerPadding: PaddingValues,
    topBarName: (String?) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Home> {
            topBarName(stringResource(R.string.home_page))
            HomeRoot {
                navController.navigate(Fact(it))
            }
        }
        composable<Fact> {
            val number = it.toRoute<Fact>().number
            topBarName(stringResource(R.string.fact_page, number))

        }

    }
}

@Serializable
object Home

@Serializable
data class Fact(val number: String)

fun NavDestination.routeClass(): KClass<*>? {
    return this
        .route
        ?.split("/")
        ?.first()
        ?.let { className ->
            generateSequence(className, ::replaceDotByDollar).mapNotNull(::tryParseClass)
                .firstOrNull()
        }
}

private fun tryParseClass(className: String): KClass<*>? {
    return runCatching { Class.forName(className).kotlin }.getOrNull()
}

private fun replaceDotByDollar(input: String): String? {
    val index = input.lastIndexOf('.')
    return if (index != -1) {
        String(input.toCharArray().apply { set(index, '$') })
    } else null
}


