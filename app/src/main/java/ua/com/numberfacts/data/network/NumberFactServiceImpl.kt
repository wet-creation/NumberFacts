package ua.com.numberfacts.data.network

import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.Results

class NumberFactServiceImpl : NumberFactService {
    override fun get(number: String): Results<String, DataError.Network> {
        TODO("Not yet implemented")
    }
}