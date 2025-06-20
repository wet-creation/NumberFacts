package ua.com.numberfacts.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

class HttpClientFactory() {
    fun getClient(): HttpClient {
        return HttpClient(OkHttp)
    }
}