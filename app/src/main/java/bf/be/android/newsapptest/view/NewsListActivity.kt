package bf.be.android.newsapptest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bf.be.android.newsapptest.databinding.ActivityNewsListBinding
import bf.be.android.newsapptest.CheckNetwork
import bf.be.android.newsapptest.model.adapters.ItemsListAdapter
import bf.be.android.newsapptest.model.apis.ApiSearch
import bf.be.android.newsapptest.model.apis.Items
import bf.be.android.newsapptest.model.apis.SearchResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class NewsListActivity : AppCompatActivity(), CheckNetwork {
    private lateinit var binding: ActivityNewsListBinding

    companion object {
        var itemsList: ArrayList<Items> = arrayListOf()
        var itemsListAdapter: RecyclerView.Adapter<ItemsListAdapter.ViewHolder>? = null
        var currentPage: Int = 1
        var lastPage: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("My News")

        // Creation of the recyclerview
        var itemsListAdapterLayoutManager: RecyclerView.LayoutManager? = null
        itemsListAdapterLayoutManager = LinearLayoutManager(this)
        val recyclerView = binding.newsList
        recyclerView.layoutManager = itemsListAdapterLayoutManager
        itemsListAdapter = ItemsListAdapter(itemsList, this)
        recyclerView.adapter = itemsListAdapter

        val searchButton: ImageButton = binding.searchButton
        searchButton.setOnClickListener {
            //TODO Handle navigation buttons state depending on current page and total pages
            val searchTxt: String = binding.searchEdit.text.toString()
            searchNews(searchTxt, currentPage)
        }
    }

    private fun searchNews (searchTxt: String, currentPage: Int) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://chroniclingamerica.loc.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiSearch::class.java)
        val call = api.getResults(searchTxt, currentPage)
        call.enqueue(object : Callback<SearchResults?> {

            override fun onResponse(call: Call<SearchResults?>, response: Response<SearchResults?>) {
                val itemsResult = response.body()?.getItemsList()!!
                lastPage = response.body()?.getLastPageNr()!!

                updateRecyclerview(itemsResult)
            }

            override fun onFailure(call: Call<SearchResults?>, t: Throwable) {
                displayEmptyList("error")
            }
        })
    }

    private fun updateRecyclerview (items: ArrayList<Items>) {
        itemsList.clear()
        for (i in items) {
            itemsList.add(i)
        }
        itemsListAdapter?.notifyDataSetChanged()

        if (lastPage == 0) {
            displayEmptyList("empty")
        } else {
            binding.searchHeader.text = "Search results for: \"" + binding.searchEdit.text + "\""
        }
    }

    private fun displayEmptyList (message: String) {
        if (message.equals("error")) {
            if (isOnline(this)) binding.searchHeader.text = "An error has occurred" else binding.searchHeader.text = "There is no Internet connection"
        } else if (message.equals("empty")) {
            binding.searchHeader.text = "There are no results for: \"" + binding.searchEdit.text + "\""
        }
    }
}