package ua.com.numberfacts.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.com.numberfacts.ui.theme.NumberFactsTheme

@Composable
fun FactRoot(
    viewModel: FactViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FactScreen(
        state = state,

        )
}

@Composable
fun FactScreen(
    state: FactState,
) {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterVertically
        ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(state.numberFact?.number.toString())
        Text(state.numberFact?.description ?: "")

    }
}

@Preview
@Composable
private fun Preview() {
    NumberFactsTheme {
        FactScreen(
            state = FactState(),
        )
    }
}