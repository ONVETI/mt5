package uz.anvar.mt5.di

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module
import retrofit2.Retrofit
import uz.anvar.mt5.data.remote.TwelveDataApi
import uz.anvar.mt5.data.repository.ForexRepository

val dataModule = module {
    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }
    single { get<Retrofit>().create(TwelveDataApi::class.java) }
    single { ForexRepository(get(), get()) }
}
