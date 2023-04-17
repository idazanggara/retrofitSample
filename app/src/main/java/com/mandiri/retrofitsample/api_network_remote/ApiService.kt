package com.mandiri.retrofitsample.api_network_remote

import com.google.gson.JsonObject
import com.mandiri.retrofitsample.api_network_remote.response.BaseResponse
import com.mandiri.retrofitsample.api_network_remote.response.BaseResponseMoshi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// nah sekarang ke JsonPlaceholderApi
// kita tinggal bikin requestnya

// karena kita sudah pakai BASE_URL, diget ini tinggal sisanya
// kita pakai anotation get

interface ApiService {
    @GET("/api/users?page=2")
    suspend fun getUser(): Response<BaseResponse>
    // suspend fun getUser(): Response<BaseResponse<ArrayList<UserResponse>>>

    // udah deh
    // kita udah bikin modelResponse
    // udah kita bikin interfaceRequestnya
    // kita udah bikin instancenya
    // tinggal kita jahit di RetrofitInstancenya

    @GET("/api/users/{id}")
    suspend fun getUserByID(@Path("id") id: String): Response<BaseResponseMoshi>
//     suspend fun getUserByID(@Path("id") id:String): Response<JsonObject>

    @PUT("/api/users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body body: JsonObject): Response<JsonObject>

    @DELETE("/api/users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<JsonObject>

    @POST("/api/users")
    suspend fun createUser(@Body body: JsonObject): Response<JsonObject>
}