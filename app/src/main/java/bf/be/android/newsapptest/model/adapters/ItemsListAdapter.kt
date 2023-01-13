package bf.be.android.newsapptest.model.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bf.be.android.newsapptest.R
import bf.be.android.newsapptest.model.apis.Items
import bf.be.android.newsapptest.view.ArticleActivity

class ItemsListAdapter(val itemsList: ArrayList<Items>, private val passedContext: Context): RecyclerView.Adapter<ItemsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ItemsListAdapter.ViewHolder, position: Int) {
        // Fields displayed on the list for each article
        holder.articleTitle.text = itemsList[position].title
        holder.articleDate.text = formatDate(itemsList[position].date.toString())
        holder.publicationLocation.text = itemsList[position].placeOfPublication
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var articleTitle: TextView
        var articleDate: TextView
        var publicationLocation: TextView

        init {
            articleTitle = itemView.findViewById(R.id.article_title)
            articleDate = itemView.findViewById(R.id.article_date)
            publicationLocation = itemView.findViewById(R.id.publication_location)

            itemView.setOnClickListener {
                // When an item is clicked, a new activity is started with this data in its intent
                val articleDetailsIntent = Intent(itemView.context, ArticleActivity::class.java)
                articleDetailsIntent.putExtra("TITLE", itemsList[absoluteAdapterPosition].title)
                articleDetailsIntent.putExtra("DATE", formatDate(itemsList[absoluteAdapterPosition].date.toString()))
                articleDetailsIntent.putExtra("PLACE_OF_PUBLICATION", itemsList[absoluteAdapterPosition].placeOfPublication)
                articleDetailsIntent.putExtra("LANGUAGE", itemsList[absoluteAdapterPosition].language)
                articleDetailsIntent.putExtra("NOTE", itemsList[absoluteAdapterPosition].note)
                articleDetailsIntent.putExtra("SUBJECT", itemsList[absoluteAdapterPosition].subject)
                articleDetailsIntent.putExtra("FREQUENCY", itemsList[absoluteAdapterPosition].frequency)
                articleDetailsIntent.putExtra("PAGE", itemsList[absoluteAdapterPosition].page)
                articleDetailsIntent.putExtra("TYPE", itemsList[absoluteAdapterPosition].type)
                articleDetailsIntent.putExtra("EDITION_LABEL", itemsList[absoluteAdapterPosition].editionLabel)
                articleDetailsIntent.putExtra("PUBLISHER", itemsList[absoluteAdapterPosition].publisher)
                articleDetailsIntent.putExtra("URL", itemsList[absoluteAdapterPosition].url)
                articleDetailsIntent.putExtra("OCR_ENG", itemsList[absoluteAdapterPosition].ocrEng)
                itemView.context.startActivity(articleDetailsIntent)
            }
        }
    }

    private fun formatDate(date: String): String {
        // Changes the date into a more readable format
        return date.slice(6..7) + "/" + date.slice(4..5) + "/" + date.slice(0..3)
    }
}