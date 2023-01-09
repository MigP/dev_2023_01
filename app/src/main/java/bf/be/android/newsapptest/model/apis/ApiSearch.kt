package bf.be.android.newsapptest.model.apis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSearch {
    @GET("search/pages/results?format=json")
    fun getResults(@Query("andtext") searchTxt: String, @Query("page") currentPage: Int): Call<SearchResults?>
}