package bonch.dev.networking.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.networking.R
import bonch.dev.networking.models.Post

class PostAdapter (val list : List<Post>, val context : Context) :
    RecyclerView.Adapter<PostAdapter.ItemPostHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPostHolder {
        return ItemPostHolder (
            LayoutInflater.from(context)
                .inflate(R.layout.item_post, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemPostHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    class ItemPostHolder (itemview : View) : RecyclerView.ViewHolder(itemview) {

        private val titlePostTextView = itemview.findViewById<TextView>(R.id.item_title)
        private val bodyPostTextView = itemview.findViewById<TextView>(R.id.item_body)

        fun bind (post : Post) {
            titlePostTextView.text = post.title
            bodyPostTextView.text = post.body
        }
    }
}

