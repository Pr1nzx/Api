package dapri.api.api.services

import dapri.api.api.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET



interface ProductService {
    @GET("product")
    fun getProducts(): Call<ProductResponse>

}