package ua.com.numberfacts.data.network

import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.Results

interface NumberFactService {

    suspend fun get(number: String): Results<String, DataError.Network>
}