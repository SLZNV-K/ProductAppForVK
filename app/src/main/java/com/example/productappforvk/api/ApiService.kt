package com.example.productappforvk.api

import com.example.productappforvk.ProductResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("products")
    suspend fun getProducts(@Query("skip") skip: Int, @Query("limit") limit: Int): Response<ProductResponse>
}

object RetrofitClient {
    private const val BASE_URL = "https://dummyjson.com/"
    private var retrofit: Retrofit? = null

    val apiService: ApiService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiService::class.java)
        }

}