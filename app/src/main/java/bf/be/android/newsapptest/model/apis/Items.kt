package bf.be.android.newsapptest.model.apis

import com.google.gson.annotations.SerializedName

data class Items (
    @SerializedName("edition") var edition: String? = null,
    @SerializedName("frequency") var frequency: String? = null,
    @SerializedName("subject") var subject: ArrayList<String> = arrayListOf(),
    @SerializedName("date") var date: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("note") var note: ArrayList<String> = arrayListOf(),
    @SerializedName("type") var type: String? = null,
    @SerializedName("place_of_publication") var placeOfPublication: String? = null,
    @SerializedName("edition_label") var editionLabel: String? = null,
    @SerializedName("publisher") var publisher: String? = null,
    @SerializedName("language") var language: ArrayList<String> = arrayListOf(),
    @SerializedName("ocr_eng") var ocrEng: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("page") var page: String? = null
)
