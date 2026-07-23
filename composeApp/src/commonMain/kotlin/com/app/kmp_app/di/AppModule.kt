package com.app.kmp_app.di

import com.app.kmp_app.data.local.LocalDataSource
import com.app.kmp_app.data.remote.RemoteDataSource
import com.app.kmp_app.data.repository.NewsRepository
import com.app.kmp_app.data.repository.NewsRepositoryImpl
import com.app.kmp_app.database.AppDatabase
import com.app.kmp_app.database.DriverFactory
import com.app.kmp_app.viewmodel.HomeViewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.bind

fun appModule() = module {
    single { NetworkModule.createHttpClient() }
    single { 
        val driverFactory: DriverFactory = get()
        AppDatabase(driverFactory.createDriver()) 
    }
    
    singleOf(::LocalDataSource)
    singleOf(::RemoteDataSource)
    singleOf(::NewsRepositoryImpl) { bind<NewsRepository>() }
    
    viewModelOf(::HomeViewModel)
}

expect fun platformModule(): Module
