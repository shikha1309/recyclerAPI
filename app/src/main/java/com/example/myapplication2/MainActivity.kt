package com.example.myapplication2

import android.os.Bundle

import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URl ="https://jsonplaceholder.typicode.com/ "


class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
//     lateinit var  myAdapter : MyAdapter
    lateinit var userList: List<ApplicationDataItem>

  private val layoutManager = LinearLayoutManager(baseContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // access recycler view
        recyclerView = findViewById(R.id.recycler_viewUsers)
        recyclerView.setHasFixedSize(true)
        val  linearLayout = LinearLayoutManager(this ,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager =layoutManager
        userList = List()
        val myAdapter = MyAdapter(this, userList )
        recyclerView.adapter =myAdapter

         getMyApplicationData()

        }

    private fun List(): List<ApplicationDataItem> {
   return emptyList()
    }
    // making a connection and fetching data
    private fun getMyApplicationData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URl)
            .build()
            .create( ApiInterface:: class.java)


            // fetching the data
            val retrofitData = retrofitBuilder .getData()
             retrofitData.enqueue(object : Callback<List<ApplicationDataItem>?> {
            override fun onResponse(call: Call<List<ApplicationDataItem>?>, response: Response<List<ApplicationDataItem>?>)
            {
                //send this response to my adapter class
              val responseBody= response.body() !!
                var myAdapter = MyAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                recyclerView.adapter=myAdapter
            // append all the data in the textView
//                val myStringBuilder = StringBuilder()
//                for(myData in responseBody){
//                   myStringBuilder.append(myData.id)
//                    myStringBuilder.append("\n")
//                    myStringBuilder.append(myData.userId)
//                    myStringBuilder.append("\n")
//                    myStringBuilder.append(myData.title)
//                    myStringBuilder.append("\n")
//                    myStringBuilder.append(myData.body)
//                    myStringBuilder.append("\n")
//                }
                 // txtId.text = myStringBuilder
//                findViewById<TextView>(R.id.txtId).text = myStringBuilder

            }

            override fun onFailure(call: Call<List<ApplicationDataItem>?>, t: Throwable) {
            d("MainActivity" ,"onFailur :  " + t.message)
            }
        })

    }
}


