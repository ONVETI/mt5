package uz.anvar.mt5.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module

data class BaseUrl(val url: String)

val networkModule = { baseUrl: BaseUrl ->

    module {
        single {
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }

//        single {
//            OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().apply {
//                    level = HttpLoggingInterceptor.Level.BODY
//                })
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .build()
//        }

//        single {
//            Retrofit.Builder()
//                .baseUrl(baseUrl.url)
//                .client(get())
//                .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
//                .build()
//        }
    }
}
