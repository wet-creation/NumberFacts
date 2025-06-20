package ua.com.numberfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.KoinAndroidContext
import ua.com.numberfacts.navigation.Home
import ua.com.numberfacts.navigation.NavigationRoot
import ua.com.numberfacts.navigation.routeClass
import ua.com.numberfacts.ui.theme.NumberFactsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var topBarTitle by remember {
                mutableStateOf<String?>(null)
            }
            val backStack by navController.currentBackStackEntryAsState()
            val showNavBack = remember {
                derivedStateOf {
                    backStack?.destination?.routeClass() != Home::class
                }
            }
            KoinAndroidContext {
                NumberFactsTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            topBarTitle?.let {
                                CenterAlignedTopAppBar(
                                    navigationIcon = {
                                        if (showNavBack.value)
                                            IconButton(
                                                onClick = {
                                                    navController.navigateUp()
                                                }
                                            ) {
                                                Icon(
                                                    contentDescription = null,
                                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack
                                                )
                                            }
                                    },
                                    title = {
                                        Text(
                                            it,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                    }
                                )
                            }
                        }) { innerPadding ->
                        NavigationRoot(
                            navController,
                            innerPadding
                        ) {
                            topBarTitle = it
                        }
                    }
                }
            }
        }
    }
}

