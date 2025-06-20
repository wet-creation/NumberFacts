package ua.com.numberfacts.utils.responses


typealias RootError = Error

sealed interface Results<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : Results<D, E>

    data class Error<out D, out E : RootError>(val error: E) : Results<D, E>
}

inline fun <T, E : RootError, R> Results<T, E>.map(map: (T?) -> R): Results<R, E> {
    return when (this) {
        is Results.Error -> Results.Error(error)
        is Results.Success -> Results.Success(map(data))
    }
}


fun <T, E : RootError> Results<T, E>.asEmptyDataResult(): EmptyDataResult<E> {
    return map { }
}

typealias EmptyDataResult<E> = Results<Unit, E>


