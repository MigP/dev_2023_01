package bf.be.android.newsapptest.model.apis

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SearchResults {
    @SerializedName("totalItems") var totalItems: Int? = null
    @SerializedName("endIndex") var endIndex: Int? = null
    @SerializedName("startIndex") var startIndex: Int? = null
    @SerializedName("itemsPerPage") var itemsPerPage: Int? = null
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()

    fun getItemsList(): ArrayList<Items> {
        return this.items
    }

    fun getLastPageNr(): Int {
        var totalPages: Int = 0
        if (this.totalItems != null) {
            totalPages = if (this.totalItems!! %20 > 0) 1 + this.totalItems!! / 20 else this.totalItems!!/20
        }

        return totalPages
    }
}