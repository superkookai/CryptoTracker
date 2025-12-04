package com.plcoding.cryptotracker.core.data.networking

import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        println("DEBUG:" + NetworkError.NO_INTERNET)
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        println("DEBUG:" + NetworkError.SERIALIZATION)
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        println("DEBUG:" + NetworkError.UNKNOWN)
        kotlin.coroutines.coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}