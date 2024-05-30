package dapri.api.api

import dapri.api.api.services.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
        private const val BASE_URL = "https://dummyjson.com"

    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val productService : ProductService by lazy {
        retrofit.create(ProductService::class.java)
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

        //klo mau nambah service bisa di copas dari product service aja


}

