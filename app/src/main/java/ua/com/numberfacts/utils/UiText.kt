package ua.com.numberfacts.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ua.com.numberfacts.R
import ua.com.numberfacts.utils.UiText.DynamicString
import ua.com.numberfacts.utils.UiText.StringResource
import ua.com.numberfacts.utils.responses.DataError

val emptyUiText = DynamicString("")

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}

fun DataError.asUiText(): UiText {
    return when (this) {

        DataError.Network.NO_INTERNET -> StringResource(
            R.string.no_internet
        )

        DataError.Network.SERVER_ERROR -> StringResource(
            R.string.server_error
        )

        DataError.Network.BAD_REQUEST -> StringResource(
            R.string.bad_request
        )

        DataError.Network.NOT_FOUND -> StringResource(
            R.string.not_found
        )

        else -> StringResource(R.string.unknown_error)
    }
}

