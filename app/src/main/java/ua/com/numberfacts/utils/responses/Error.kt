package ua.com.numberfacts.utils.responses

sealed interface Error

sealed interface DataError: Error {
    enum class Network: DataError {
        NO_INTERNET,
        SERVER_ERROR,
        BAD_REQUEST,
        NOT_FOUND,
        UNKNOWN,
        CLIENT_EXCEPTION,
    }
}




