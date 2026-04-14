package uz.anvar.mt5.di

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val dataModule = module {
    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }
}
