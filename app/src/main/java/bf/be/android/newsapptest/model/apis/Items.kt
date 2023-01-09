package bf.be.android.newsapptest.model.apis

import com.google.gson.annotations.SerializedName

data class Items (
    @SerializedName("sequence") var sequence: Int? = null,
    @SerializedName("county") var county: ArrayList<String> = arrayListOf(),
    @SerializedName("edition") var edition: String? = null,
    @SerializedName("frequency") var frequency: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("subject") var subject: ArrayList<String> = arrayListOf(),
    @SerializedName("city") var city: ArrayList<String> = arrayListOf(),
    @SerializedName("date") var date: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("end_year") var endYear: Int? = null,
    @SerializedName("note") var note: ArrayList<String> = arrayListOf(),
    @SerializedName("state") var state: ArrayList<String> = arrayListOf(),
    @SerializedName("section_label") var sectionLabel: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("place_of_publication") var placeOfPublication: String? = null,
    @SerializedName("start_year") var startYear: Int? = null,
    @SerializedName("edition_label") var editionLabel: String? = null,
    @SerializedName("publisher") var publisher: String? = null,
    @SerializedName("language") var language: ArrayList<String> = arrayListOf(),
    @SerializedName("alt_title") var altTitle: ArrayList<String> = arrayListOf(),
    @SerializedName("lccn") var lccn: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("ocr_eng") var ocrEng: String? = null,
    @SerializedName("batch") var batch: String? = null,
    @SerializedName("title_normal") var titleNormal: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("place") var place: ArrayList<String> = arrayListOf(),
    @SerializedName("page") var page: String? = null
)
