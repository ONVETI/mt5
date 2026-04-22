package uz.anvar.mt5.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TwelveDataWebSocketResponse(
    @SerialName("event") val event: String? = null,
    @SerialName("symbol") val symbol: String? = null,
    @SerialName("price") val price: Double? = null,
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("status") val status: Int? = null,
)
