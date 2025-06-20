package ua.com.numberfacts

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import ua.com.numberfacts.data.di.DbModule
import ua.com.numberfacts.data.di.NetworkModule
import ua.com.numberfacts.data.di.NumberDataModule

val ApplicationModule = module {
    single<CoroutineScope> {
        (androidApplication() as NumberFactsApp).applicationScope
    }
}

class NumberFactsApp: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NumberFactsApp)
            modules(
               ApplicationModule,
                DbModule,
                NetworkModule,
                NumberDataModule,
            )
        }
    }

}