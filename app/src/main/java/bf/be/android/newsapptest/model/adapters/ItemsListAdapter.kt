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
        holder.articleDate.text = itemsList[position].date
        holder.publicationLocation.text = itemsList[position].placeOfPublication
    }

    inner class ViewHolder(itemView: View, passedContext: Context): RecyclerView.ViewHolder(itemView) {
        var articleTitle: TextView //TODO format date correctly
        var articleDate: TextView
        var publicationLocation: TextView

        init {
            articleTitle = itemView.findViewById(R.id.article_title)
            articleDate = itemView.findViewById(R.id.article_date)
            publicationLocation = itemView.findViewById(R.id.publication_location)

            itemView.setOnClickListener {
//                // Displays the detailed view of this list
//                val createListIntent = Intent(itemView.context, MainActivity::class.java)
//                createListIntent.putExtra("targetFragment", "create")
//                createListIntent.putExtra("fragmentMode", "listViewing")
//                createListIntent.putExtra("listId", listsArray[adapterPosition].getListId().toString()) // Adds the id of the list just clicked and passes it on to the create fragment
//                itemView.context.startActivity(createListIntent)
            }
        }
    }
}