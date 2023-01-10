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

class ItemsListAdapter(val itemsList: ArrayList<Items>, val passedContext: Context): RecyclerView.Adapter<ItemsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
        return ViewHolder(v, passedContext)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ItemsListAdapter.ViewHolder, position: Int) {
        holder.articleTitle.text = itemsList[position].title
        holder.articleDate.text = formatDate(itemsList[position].date.toString())
        holder.publicationLocation.text = itemsList[position].placeOfPublication
    }

    inner class ViewHolder(itemView: View, passedContext: Context): RecyclerView.ViewHolder(itemView) {
        var articleTitle: TextView
        var articleDate: TextView
        var publicationLocation: TextView

        init {
            articleTitle = itemView.findViewById(R.id.article_title)
            articleDate = itemView.findViewById(R.id.article_date)
            publicationLocation = itemView.findViewById(R.id.publication_location)

            itemView.setOnClickListener {
                val articleDetailsIntent = Intent(itemView.context, ArticleActivity::class.java)
                articleDetailsIntent.putExtra("TITLE", itemsList[adapterPosition].title)
                articleDetailsIntent.putExtra("DATE", formatDate(itemsList[adapterPosition].date.toString()))
                articleDetailsIntent.putExtra("PLACE_OF_PUBLICATION", itemsList[adapterPosition].placeOfPublication)
                articleDetailsIntent.putExtra("LANGUAGE", itemsList[adapterPosition].language)
                articleDetailsIntent.putExtra("NOTE", itemsList[adapterPosition].note)
                articleDetailsIntent.putExtra("SUBJECT", itemsList[adapterPosition].subject)
                articleDetailsIntent.putExtra("FREQUENCY", itemsList[adapterPosition].frequency)
                articleDetailsIntent.putExtra("PAGE", itemsList[adapterPosition].page)
                articleDetailsIntent.putExtra("TYPE", itemsList[adapterPosition].type)
                articleDetailsIntent.putExtra("EDITION_LABEL", itemsList[adapterPosition].editionLabel)
                articleDetailsIntent.putExtra("PUBLISHER", itemsList[adapterPosition].publisher)
                articleDetailsIntent.putExtra("URL", itemsList[adapterPosition].url)
                articleDetailsIntent.putExtra("OCR_ENG", itemsList[adapterPosition].ocrEng)
                itemView.context.startActivity(articleDetailsIntent)
            }
        }
    }

    private fun formatDate(date: String): String {
        return date.slice(6..7) + "/" + date.slice(4..5) + "/" + date.slice(0..3)
    }
}