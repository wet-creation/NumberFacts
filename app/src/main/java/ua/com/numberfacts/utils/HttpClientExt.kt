package ua.com.numberfacts.utils


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import ua.com.numberfacts.utils.Config.baseUrl
import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.Results
import java.net.ConnectException
import java.net.UnknownHostException

fun constructRoute(route: String): String {
    return when {
        route.contains(baseUrl) -> route
        route.startsWith("/") -> "$baseUrl$route"
        else -> "$baseUrl/$route"
    }
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): Results<T, DataError.Network> {
    return when (response.status.value) {
        in 200..299 -> Results.Success(response.body())
        400 -> Results.Error(DataError.Network.BAD_REQUEST)
        401 -> Results.Error(DataError.Network.UNAUTHORIZED)
        403 -> Results.Error(DataError.Network.FORBIDDEN)
        404 -> Results.Error(DataError.Network.NOT_FOUND)
        408 -> Results.Error(DataError.Network.REQUEST_TIMEOUT)
        409 -> Results.Error(DataError.Network.CONFLICT)
        413 -> Results.Error(DataError.Network.PAYLOAD_TOO_LARGE)
        418 -> Results.Error(DataError.Network.CLIENT_EXCEPTION)
        429 -> Results.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Results.Error(DataError.Network.SERVER_ERROR)
        else -> Results.Error(DataError.Network.UNKNOWN)
    }
}

suspend inline fun <reified Response : Any> HttpClient.get(
    route: String,
    queryParameters: Map<String, Any?> = mapOf()
): Results<Response, DataError.Network> {
    return safeCall {
        get {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Results<T, DataError.Network> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return Results.Error(DataError.Network.NO_INTERNET)
    } catch (e: ConnectException) {
        e.printStackTrace()
        return Results.Error(DataError.Network.NO_INTERNET)
    }
    catch (e: UnknownHostException) {
        e.printStackTrace()
        return Results.Error(DataError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return Results.Error(DataError.Network.SERIALIZATION)
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        return Results.Error(DataError.Network.UNKNOWN)
    }
    return responseToResult(response)
}