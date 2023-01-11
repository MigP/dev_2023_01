package bf.be.android.newsapptest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bf.be.android.newsapptest.databinding.ActivityNewsListBinding
import bf.be.android.newsapptest.CheckNetwork
import bf.be.android.newsapptest.R
import bf.be.android.newsapptest.model.adapters.ItemsListAdapter
import bf.be.android.newsapptest.model.apis.ApiSearch
import bf.be.android.newsapptest.model.apis.Items
import bf.be.android.newsapptest.model.apis.SearchResults
import com.bumptech.glide.Glide
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
        var searchTxt: String = ""
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

        Glide.with(this).load(R.drawable.waiting).into(binding.waitingPlaceholder)

        // Displays initial list
        searchNews("", currentPage)

        val searchButton: ImageButton = binding.searchButton
        searchButton.setOnClickListener {
            searchTxt = binding.searchEdit.text.toString()
            currentPage = 1
            searchNews(searchTxt, currentPage)
        }

        val nextButton: Button = binding.nextButton
        nextButton.setOnClickListener {
            currentPage++
            searchNews(searchTxt, currentPage)
        }

        val previousButton: Button = binding.previousButton
        previousButton.setOnClickListener {
            currentPage--
            searchNews(searchTxt, currentPage)
        }
    }

    private fun searchNews (searchTxt: String, currentPage: Int) {
        binding.waitingPlaceholder.isVisible = true
        
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
                updateButtons()
                updateHeaders()
                binding.waitingPlaceholder.isVisible = false
            }

            override fun onFailure(call: Call<SearchResults?>, t: Throwable) {
                displayEmptyList("error")
                binding.waitingPlaceholder.isVisible = false
            }
        })
    }

    private fun updateButtons () {
        binding.previousButton.isEnabled = true
        binding.nextButton.isEnabled = true

        if (currentPage == 1) {
            binding.previousButton.isEnabled = false

        }

        if (currentPage == lastPage) {
            binding.nextButton.isEnabled = false
        }

        if (lastPage == 0) {
            binding.previousButton.isEnabled = false
            binding.nextButton.isEnabled = false
        }
    }

    private fun updateRecyclerview (items: ArrayList<Items>) {
        itemsList.clear()
        for (i in items) {
            itemsList.add(i)
        }
        itemsListAdapter?.notifyDataSetChanged()
    }

    private fun updateHeaders () {
        if (lastPage == 0) {
            displayEmptyList("empty")
            binding.searchPage.text = ""
        } else if (binding.searchEdit.text.toString() == ""){
            binding.searchHeader.text = ""
            binding.searchPage.text = "Page " + currentPage + " of " + lastPage
        } else {
            binding.searchHeader.text = "Search results for: \"" + searchTxt + "\""
            binding.searchPage.text = "Page " + currentPage + " of " + lastPage
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