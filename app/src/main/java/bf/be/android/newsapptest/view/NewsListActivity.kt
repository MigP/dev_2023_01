package bf.be.android.newsapptest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import bf.be.android.newsapptest.databinding.ActivityNewsListBinding
import bf.be.android.newsapptest.model.CheckNetwork
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
        var currentPage: Int = 1
        var lastPage: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("My News")

        val searchButton: ImageButton = binding.searchButton

        searchButton.setOnClickListener {
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
            var itemsList: ArrayList<Items> = arrayListOf()

            override fun onResponse(call: Call<SearchResults?>, response: Response<SearchResults?>) {
                itemsList = response.body()?.getItemsList()!!
                lastPage = response.body()?.getLastPageNr()!!

                createRecyclerview(itemsList)
            }

            override fun onFailure(call: Call<SearchResults?>, t: Throwable) {
                displayEmptyList("error")
            }
        })
    }

    private fun createRecyclerview (items: ArrayList<Items>) {
        if (lastPage > 0) {
            println("------------------ item1 title: " + items[0].title) //TODO Create recyclerview
        } else {
            displayEmptyList("empty")
        }

        //TODO ------------------------ This happens on item click
        val nextBtn: Button = binding.nextButton
        nextBtn.setOnClickListener {
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("TITLE", items[0].title)
            startActivity(intent)
        }
        //TODO ------------------------------------------------



    }

    private fun displayEmptyList (message: String) {
        if (message.equals("error")) {
            if (isOnline(this)) println("An error has occurred") else println("There is no Internet connection")
        } else if (message.equals("empty")) {
            println("There are no results for this search") //TODO Display error on list
        }
    }
}