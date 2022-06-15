package com.example.myapplication.data.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Breed

class BreedsAdapter(private val clickAction: (Breed) -> Unit,
                    private val favAction: (Breed) -> Unit
) : ListAdapter<Breed, BreedsAdapter.BreedsViewHolder>(DiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BreedsViewHolder(inflater.inflate(R.layout.item_breed, parent,false))
    }

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        val breed = getItem(position)
        holder.breed.text = breed!!.name
        holder.breed.setOnClickListener {
            clickAction(breed)
        }

        if (breed.fav)
            holder.fav.setImageResource(R.drawable.ic_favourite)
        else
            holder.fav.setImageResource(R.drawable.ic_favorite_empty)

        holder.fav.setOnClickListener {
            favAction(breed)
        }

    }

    private class DiffCallback : DiffUtil.ItemCallback<Breed>(){
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem == newItem
        }

    }

    inner class BreedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breed = itemView.findViewById<TextView>(R.id.tv_breed)
        val fav = itemView.findViewById<ImageView>(R.id.fav)
    }
}