package bf.be.android.newsapptest.model.apis

import com.google.gson.annotations.SerializedName

class SearchResults {
    @SerializedName("totalItems") var totalItems: Int? = null
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()

    fun getItemsList(): ArrayList<Items> {
        return this.items
    }

    fun getLastPageNr(): Int {
        var totalPages = 0
        if (this.totalItems != null) {
            totalPages = if (this.totalItems!! %20 > 0) 1 + this.totalItems!! / 20 else this.totalItems!!/20
        }

        return totalPages
    }
}