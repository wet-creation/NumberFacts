package ua.com.numberfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext
import ua.com.numberfacts.domain.NumberFact
import ua.com.numberfacts.domain.NumberFactsRepository
import ua.com.numberfacts.ui.theme.NumberFactsTheme
import java.math.BigInteger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repo by inject<NumberFactsRepository>()

        setContent {
            KoinAndroidContext {
                var number by remember { mutableStateOf(NumberFact()) }

                LaunchedEffect(null) {
                    repo.fetch(BigInteger("2"))
                    repo.get(BigInteger("2")).collect {
                        number = it
                    }
                }
                NumberFactsTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            Text(number.number.toString())
                            Text(number.description)
                        }
                    }
                }
            }
        }
    }
}

