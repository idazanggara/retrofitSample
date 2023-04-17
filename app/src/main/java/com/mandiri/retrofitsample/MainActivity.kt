package com.mandiri.retrofitsample

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.gson.JsonObject
import com.mandiri.retrofitsample.adapters.RVAdapter
import com.mandiri.retrofitsample.api_network_remote.ApiService
import com.mandiri.retrofitsample.api_network_remote.RetrofitInstance
import com.mandiri.retrofitsample.api_network_remote.response.DataItem
import com.mandiri.retrofitsample.databinding.ActivityMainBinding
import com.mandiri.retrofitsample.utils.DummyDataItems
import kotlinx.coroutines.launch

/*
 *
 *
 * Retrofit = Untuk Api Client
 *
 * langkah pertama add dependensi retrofit, ada 2 retrofit dan moshing
 *
 * sebenernya retrofit itu adalah framework juga ya, di dalamnya itu ada core http clinetnya sendir juga tapi sudah di bungkus agar lebih mudah gunakan
 *
 * kemudian kita membuat sebuah instance atau http client buat settingan configurasinya
 * kita buat class abstract RetrofitInstance
 *
 * langkah selanjutnya membuat sebuah interface
 *
 *
 * */
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // ini untu api servicenya
    private lateinit var apiService: ApiService
    private lateinit var apiServiceMoshi: ApiService
    // ini untuk simulasi loadingnya, kalau kalian nanti bisa buat layout sendiri untuk loading
    private var progressDialog: ProgressDialog? = null
    private lateinit var rvAdapter: RVAdapter
    private lateinit var layoutManager: LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // val apiService = RetrofitInstance.getJsonPlaceholderApi() // nah ini kalau kita panggil fungsi
        apiService = RetrofitInstance.apiService
        apiServiceMoshi = RetrofitInstance.apiServiceMoshi

        binding.apply {
            btnGet.setOnClickListener {
                getUser()
                setUpRecycleView()
            }
            btnGetById.setOnClickListener {
                getUserByID()

            }
            btnUpdate.setOnClickListener {
                updateUser()

            }
            btnDelete.setOnClickListener {
                deleteUser()

            }
            btnPost.setOnClickListener {
                createUser()

            }
        }

    }

    private fun setUpRecycleView() {
        rvAdapter = RVAdapter(arrayListOf(), object : RVAdapter.onAdapterListener{
            override fun onClick(data: DataItem) {
                Toast.makeText(this@MainActivity,"""
                    Name : ${data.firstName} ${data.last_name} 
                    Email : ${data.email}
                    Avatar Url : ${data.avatar}
                    """.trimIndent(), Toast.LENGTH_SHORT).show()
            }

        })

        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = rvAdapter
        }
    }

//    fun setDataToAdapter(data: )

    private fun createUser() {
        lifecycleScope.launch {
            showLoading("Post, Please wait....")
            val name = "Anggara"
            val job = "JavaScript Developer"
            val body = JsonObject().apply {
                addProperty("name", name)
                addProperty("job", job)
            }
            val result = apiService.createUser(body)
            if (result.isSuccessful){
                Log.e("Yeay Post Data","createUser success: ${result.body()}")
            } else {
                Log.e("Oh noo, error in Post Data","createUser field: ${result.message()}")
            }
            progressDialog?.dismiss()
        }

    }

    private fun deleteUser() {
        lifecycleScope.launch {
            showLoading("Deleting, Please wait....")
            val id = "2"
            val result = apiService.deleteUser(id)
            if(result.isSuccessful){
                // Delete berhasil
                Log.e("Yeay Delete Data","deleteUser success: ${result.body()}")
                Log.e("Check data","data: ${DummyDataItems.dataItem}")
            } else {
                Log.e("Oh noo, error in Delete Data","deleteUser field: ${result.message()}")
            }
            progressDialog?.dismiss()
        }

    }

    private fun updateUser() {
        lifecycleScope.launch {
            showLoading("Updating, Please wait....")
            val name = "Idaz coding delivery"
            val job = "Frontend Developer"
            val body = JsonObject().apply {
                addProperty("name", name)
                addProperty("job", job)
            }
            val id = "2"
            val result = apiService.updateUser(id, body)
            if (result.isSuccessful){
                Log.e("Yeay Update Data","updateUser success: ${result.body()}")
                Log.e("Check data","data: ${DummyDataItems.dataItem}")
            } else {
                Log.e("Oh noo, error in Update Data","updateUser field: ${result.message()}")
            }
            progressDialog?.dismiss()
        }

    }

    private fun getUserByID() {
        lifecycleScope.launch {
            showLoading("Getting By ID, Please wait....")
            val id = "2"
//            val result = apiServiceMoshi.getUserByID(id)
            val result = apiService.getUserByID(id)
            if (result.isSuccessful){
                Log.e("Yeay GetByID Data", "getUserByID success: ${result.body()}")
                Log.e("Check data","data: ${DummyDataItems.dataItem}")
            } else {
                Log.e("Oh noo, error in GetByID Data", "getUserByID field: ${result.message()}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun getUser() {
        // CoroutineScope(Dispatchers.Main).launch {  }
        lifecycleScope.launch {
            showLoading("Getting, Please wait....")
            val result = apiService.getUser()
            if(result.isSuccessful){
                Log.e("Yeay Get Data","getUsert success: ${result.body()?.data}")
                rvAdapter.setData(result.body()?.data as List<DataItem>)
                DummyDataItems.dataItem = result.body()?.data  as List<DataItem>
            } else {
                Log.e("Oh noo, error in Get Data", "getUser field: ${result.message()}")
            }
            progressDialog?.dismiss()
        }
    }


    private fun showLoading(msg: String){
        progressDialog = ProgressDialog.show(this@MainActivity, null,msg,true)
    }
}