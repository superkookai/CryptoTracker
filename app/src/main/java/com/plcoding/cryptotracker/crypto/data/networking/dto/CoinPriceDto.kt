package com.plcoding.cryptotracker.crypto.data.networking.dto

import com.plcoding.cryptotracker.crypto.domain.CoinPrice
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId

@Serializable
data class CoinPriceDto(
    val priceUsd: Double,
    val time: Long
)
