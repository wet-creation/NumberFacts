package ua.com.numberfacts.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.com.numberfacts.R
import ua.com.numberfacts.ui.theme.NumberFactsTheme
import ua.com.numberfacts.utils.emptyUiText

@Composable
fun FactRoot(
    viewModel: FactViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FactScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun FactScreen(
    state: FactState,
    onAction: (FactAction) -> Unit
) {

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
    if (state.error != emptyUiText)
        AlertDialog(
            title = {
                Text(text = stringResource(R.string.error_title))
            },
            text = {
                Text(text = state.error.asString())
            },
            onDismissRequest = {
                onAction(FactAction.DismissError)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onAction(FactAction.DismissError)
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onAction(FactAction.TryAgain)
                    }
                ) {
                    Text(stringResource(R.string.try_again))
                }
            }
        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(state.numberFact?.number ?: "")
        Text(state.numberFact?.description ?: "")

    }


}

@Preview
@Composable
private fun Preview() {
    NumberFactsTheme {
        FactScreen(
            state = FactState(),
        ) {}
    }
}