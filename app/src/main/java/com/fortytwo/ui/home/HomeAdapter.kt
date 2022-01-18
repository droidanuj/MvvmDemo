package com.fortytwo.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fortytwo.R
import com.fortytwo.model.Dog
import com.fortytwo.utils.ImageLoader
import kotlinx.android.synthetic.main.item_doggo.view.*

class HomeAdapter(
    private val context: Context,
    private var mList: List<Dog>
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doggo, parent, false)
        return ViewHolder(view)
    }

    fun updateList(mList : List<Dog>){
        this.mList = mList
        notifyDataSetChanged()
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.tvBreedName.text = item.breed
        item.imageUsl?.let { it1 -> ImageLoader.loadImageWithCircularCrop(context, it1, holder.ivDogImage, holder.progressBar) }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvBreedName: TextView = itemView.findViewById(R.id.breed_name)
        val ivDogImage: ImageView = itemView.findViewById(R.id.episode_item_image)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_circular)

    }
}
