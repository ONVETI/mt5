package uz.anvar.mt5.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

interface TwelveDataApi {

    @GET("time_series")
    suspend fun getTimeSeries(
        @Query("symbol") symbol: String,
        @Query("interval") interval: String,
        @Query("apikey") apiKey: String,
        @Query("outputsize") outputSize: Int = 50,
    ): TimeSeriesResponse
}

@Serializable
data class TimeSeriesResponse(
    @SerialName("values") val values: List<CandleResponse>? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("message") val message: String? = null,
)

@Serializable
data class CandleResponse(
    @SerialName("datetime") val datetime: String,
    @SerialName("open") val open: String,
    @SerialName("high") val high: String,
    @SerialName("low") val low: String,
    @SerialName("close") val close: String,
)
