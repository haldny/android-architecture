package com.digigene.android.moviefinder.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.digigene.android.moviefinder.R
import com.digigene.android.moviefinder.model.entity.Movie
import kotlinx.android.synthetic.main.item.view.*

class MoviesAdapter(val onClick: (item: Movie) -> Unit) : RecyclerView.Adapter<MoviesAdapter.Holder>() {
    var mList: List<Movie> = arrayListOf()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.item_textView.text = "${mList[position].year}: ${mList[position].title}"
        holder.itemView.setOnClickListener { onClick(mList[position]) }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateList(list: List<Movie>) {
        mList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item, parent, false)
        return Holder(view)
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}