package ua.com.numberfacts.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ua.com.numberfacts.R
import ua.com.numberfacts.ui.theme.NumberFactsTheme

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = koinViewModel(),
    navigateToFact: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onAction = {
            viewModel.onAction(action = it)
            when (it) {
                is HomeAction.NavigateToFact -> navigateToFact(it.number)
                else -> Unit
            }
        }
    )
}

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
) {
    var value by remember(state.numberValue) {
        mutableStateOf(state.numberValue)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            Alignment.CenterHorizontally
        ) {
            TextField(
                value = value,
                onValueChange = {
                    val allowedInput = it.matches(Regex("-?\\d*"))
                    if (allowedInput) {
                        value = it
                        if (it.matches(Regex("-?\\d+")))
                            onAction(HomeAction.EnterNumber(it))
                    }

                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )

            Button(onClick = {
                if (state.numberValue.isNotEmpty())
                    onAction(HomeAction.NavigateToFact(state.numberValue))
            }) {
                Text(stringResource(R.string.discover_fact))
            }
            Button(onClick = {
                val rand = (Long.MIN_VALUE..Long.MAX_VALUE).random()
                onAction(HomeAction.NavigateToFact(rand.toString()))
            }) {
                Text(stringResource(R.string.random_fact))
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            itemsIndexed(state.history, key = { index, item -> index }) { index, item ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.clickable {
                        onAction(HomeAction.NavigateToFact(item.number.toString()))
                    }) {
                    if (index != 0)
                        HorizontalDivider()

                    Text(item.number.toString())
                    Text(
                        item.description.toString(), maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

        }
    }
}

@Preview
@Composable
private fun Preview() {
    NumberFactsTheme {
        HomeScreen(
            state = HomeState(isLoading = true),
            onAction = {}
        )
    }
}