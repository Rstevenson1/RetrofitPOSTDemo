package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitdemo.adapter.MyAdapter
import com.example.retrofittest.MainViewModelFactory
import com.example.retrofitdemo.repository.Repository
import com.example.retrofittest.model.Post
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerview()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)



//        I met the below lines of code earlier here
//        viewModel.getCustomPosts(5,"id", "desc")
//        viewModel.myCustomPosts.observe(this, Observer { response ->
    //        if(response.isSuccessful){
    //            response.body()?.let { myAdapter.setData(it) }
    //        }else {
    //            Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
    //        }
    //    })
//        and the above code was the previous line of code in use for what's now below I changed to this present one

        val myPost = Post(2, 2, "Lawrence", "Electrical Engineering")
        viewModel.pushPost(myPost)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("Main", response.body().toString())
                Log.d("Main", response.body().toString())
                Log.d("Main", response.message())
            }else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setupRecyclerview() {
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}