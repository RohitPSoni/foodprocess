package com.example.orders.module

import com.example.orders.BuildConfig
import com.example.orders.mapper.DetailOrderMapper
import com.example.orders.mapper.DetailOrderMapperImpl
import com.example.orders.mapper.IngridientsMapper
import com.example.orders.mapper.IngridientsMapperImpl
import com.example.orders.mapper.OrdersMapper
import com.example.orders.mapper.OrdersMapperImpl
import com.example.orders.network.Api
import com.example.orders.network.FileInterceptor
import com.example.orders.repository.AcceptOrderRepo
import com.example.orders.repository.AcceptOrderRepoImpl
import com.example.orders.repository.ShowIngredientRepository
import com.example.orders.repository.ShowIngredientRepositoryImpl
import com.example.orders.ui.acceptorder.viewmodel.ShowOrderItemViewModel
import com.example.orders.ui.acceptorder.viewmodel.ShowOrderViewModel
import com.example.orders.ui.orderdetail.viewmodel.OrderDetailViewModel
import com.example.orders.ui.showingredient.viewmodel.ShowIngredientViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {
    single { FileInterceptor(get()) }
    factory { provideOkHttpClient(get()) }
    factory { provideExchangeApi(get()) }
    single { provideRetrofit(get()) }
}

val repoModule = module {
    single { AcceptOrderRepoImpl(get(), get(), get()) } bind AcceptOrderRepo::class
    single {
        ShowIngredientRepositoryImpl(get(), get())
    } bind ShowIngredientRepository::class
}

val provideMapper = module {
    single { OrdersMapperImpl() } bind OrdersMapper::class
    single { IngridientsMapperImpl() } bind IngridientsMapper::class
    single { DetailOrderMapperImpl() } bind DetailOrderMapper::class
}

val viewModelModule = module {
    viewModel { ShowOrderViewModel(get()) }
    viewModel { ShowOrderItemViewModel() }
    viewModel { ShowIngredientViewModel(get()) }
    viewModel { OrderDetailViewModel(get()) }
}

fun provideOkHttpClient(interceptor: FileInterceptor): OkHttpClient {
    val client = OkHttpClient().newBuilder()
    if (BuildConfig.DEBUG) {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        client.addInterceptor(logger)
        client.addInterceptor(interceptor)
    }
    return client.build()
}

fun provideExchangeApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Builder().baseUrl("https://google.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}