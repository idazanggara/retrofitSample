package com.mandiri.retrofitsample.api_network_remote

import com.mandiri.retrofitsample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


abstract class RetrofitInstance {
    companion object{
        private const val baseUrl ="https://reqres.in/"



        fun getRetrofit(): Retrofit {
            val loggingInterceptor = if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY)
            else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // ini yg ngeconver data json ke aplikasi kita, bisa pakai moshi atau Gson
                .client(client)
                .build()
        }

        fun getRetrofitMoshi() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        // kita buat dulu responnya


        val apiService: ApiService by lazy {
            getRetrofit().create(ApiService::class.java)
        }

        val apiServiceMoshi: ApiService by lazy {
            getRetrofitMoshi().create(ApiService::class.java)
        }
        // fun apiService(): ApiService = getRetrofit().create(JsonPlaceholderApi::class.java)

//        fun getApiServiceFun(): ApiService {
//            val loggingInterceptor = if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(
//                HttpLoggingInterceptor.Level.BODY)
//            else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
//            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
//            return retrofit.create(ApiService::class.java)
//        }
    }
}

/*

abstract class RetrofitInstance {
    companion object{
        private fun getRetrofit() = Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/") // tapi ini tidak disarankan, kalau di projec bisa jadi https://prod.be.perusahaan.com/ /uat/sit/dev. ini bisa ganti, biasanya di tempatkan di gradle app
            .baseUrl(BuildConfig.BASE_URL)
            // BuildConfig kadang lama, perlu di reloadIDE atau rebuilt
            // jadi BASE_URL ini kita sudah di buatkan otomatis sebuah class oleh si andorid, maka dari itu kita kasih \"
            .addConverterFactory(MoshiConverterFactory.create()) // Moshi ini hanya sebuah merek, jadi ada yg pakai gason ada yg pakai moshi. ini digunakan untuk ngomong ke jasonnya. dari object jadi jsonya
            // gw ikutin mandiri pakai moshi
            .build()
        // ini sudah dapet retrofitnya

// langkah selanjutnya membuat sebuah interface
// ini bisa custumer API, BookApi dll
// sebelum itu kita bisa buat model responnya dulu, disini kita bikin dataclassnya
// kita bikin data class Post, lihat datanya dari mana? https://jsonplaceholder.typicode.com/posts/1

        // ini proses penjahitan kalau sudah semua
        // ini kita bisa bikin fungsi atau properti, cara pertama kita buat fungsi dulu
//        fun getJsonPlaceholderApi(): JsonPlaceholderApi = getRetrofit().create(JsonPlaceholderApi::class.java)
        // sehabis ini kita buat sebuah repo

        // dicoba dulu diatas pakai function, lalu kita juga bisa buat menggunakan property dengan delegation lazy
        val jsonPlaceholderApi: JsonPlaceholderApi by lazy {
            getRetrofit().create(JsonPlaceholderApi::class.java)
        }
        // jadi ini alternatif aja
        // pertanyaannya apa bedanya? dan kalau funtion itu memungkinkan diminta sebuah argument. kalau variable enggak bisa
        // kalau cara panjangnya gimana
//        var apiKu: JsonPlaceholderApi? = null
//        fun getJsonPlaceholderApi(): JsonPlaceholderApi {
//           apiKu = getRetrofit().create(JsonPlaceholderApi::class.java)
//        }
//        nah itukan panjang, nah itu semua jadi lebih pendek seperti diatas

        // Retrofitnya juga bisa kita ganti
        /*
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
        * */
    }
}
 */
