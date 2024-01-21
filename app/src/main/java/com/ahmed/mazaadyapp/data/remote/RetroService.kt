package com.ahmed.mazaadyapp.data.remote

import com.ahmed.mazaadyapp.data.remote.dto.CategoriesDTO
import com.ahmed.mazaadyapp.data.remote.dto.OptionsDTO
import com.ahmed.mazaadyapp.data.remote.dto.PropertiesDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {

    @GET("api/v1/get_all_cats")
    @Headers("""private-key:3%o8i}_;3D4bF]G5@22r2)Et1&mLJ4?$@+16""")
    suspend fun getCategories():CategoriesDTO

    @GET("api/v1/properties")
    @Headers("""private-key:3%o8i}_;3D4bF]G5@22r2)Et1&mLJ4?$@+16""")
    suspend fun getProperties(@Query("cat") id: Int):PropertiesDTO

    @GET("api/v1/get-options-child/{id}")
    @Headers("""private-key:3%o8i}_;3D4bF]G5@22r2)Et1&mLJ4?$@+16""")
    suspend fun getOptions(@Path("id") id: Int):OptionsDTO
}