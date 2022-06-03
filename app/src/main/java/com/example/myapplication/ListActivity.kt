package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.DogsAPIClient
import com.example.myapplication.data.cb.DataRetriever
import com.example.myapplication.data.model.Breed

private const val TAG ="ListActivity"

class ListActivity : AppCompatActivity(), DataRetriever {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        findViewById<RecyclerView>(R.id.rv_breeds).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = BreedsAdapter()
        }

        DogsAPIClient.getListOfBreeds(this)
    }

    override fun onDataFetchSuccess(breeds: List<Breed>) {
        Log.d(TAG, "onDataFetchSuccess")

        val adapter = findViewById<RecyclerView>(R.id.rv_breeds).adapter as BreedsAdapter
        adapter.submitList(breeds)
    }

    override fun onDataFetchedFailed() {
        Log.d(TAG, "onDataFetchSuccess")
    }
}
