package com.example.sampleproject1

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleproject1.Retrofit.PostsData
import com.example.sampleproject1.Retrofit.PostsDataItem
import com.example.sampleproject1.Retrofit.PostsInstance
import com.example.sampleproject1.Room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@Suppress("DEPRECATION")

class MainActivity : AppCompatActivity() {
    private lateinit var database: RoomDb
    private lateinit var recyclerView: RecyclerView
    private val madapter by lazy {
        MainAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.mainRecyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = madapter
        }
        CheckConnectionType()

    }


    fun CheckConnectionType() {
            val connectionManager =
                this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val wifi_Connection = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val mobile_data_connection =connectionManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

            if (wifi_Connection!!.isConnectedOrConnecting||mobile_data_connection!!.isConnectedOrConnecting) {

                // Api showing data method and set to the Room
                ApiData()
                Toast.makeText(applicationContext, "Data Connection Established", Toast.LENGTH_SHORT).show()
            }
            else{
                ShowingData()
                //Get Api Data from Database i,e:Room

            }

        }
    private fun ApiData() {
        lifecycleScope.launch() {
            val api_data = withContext(Dispatchers.IO) {
                PostsInstance.getPhotosService().getphotosdata()
            }
            if (api_data.isSuccessful) {
                madapter.receiveData(api_data.body() ?: PostsData())
//                        Log.d("TAG", "onCreate: ${api_data.body()}")
            }

            val insertData = withContext(Dispatchers.IO) {
                api_data.body()?.let {
                    RoomDb.getInstance(applicationContext).dao()
                        .getInsertAPiData(it)
                }
            }
        }
    }
    // showing data from the database i,e. Room
    private fun ShowingData() {


        Toast.makeText(applicationContext,"Internet not connected",Toast.LENGTH_SHORT).show()
    }
    }






















//                    withContext(Dispatchers.IO){
//                        Room.databaseBuilder(applicationContext,RoomDb::class.java,"ApiDatabase").build()
//
//                    }
//                    withContext(Dispatchers.IO){
//                Room.databaseBuilder(
//                    applicationContext, com.example.sampleproject1.Room.RoomDb::class.java,
//                    "").build()

//                (database as com.example.sampleproject1.Room.RoomDb).dao()
//                    .getInsertAPiData()



