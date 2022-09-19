package com.babel.demo.dogapp.presentation.features.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babel.demo.dogapp.R
import com.babel.demo.dogapp.databinding.ViewItemDogBinding
import com.babel.demo.dogapp.domain.model.Dog
import com.babel.demo.dogapp.utils.extensions.loadImage
import kotlin.properties.Delegates

class DogAdapter(private val onItemClicked: (dogClicked: Dog) -> Unit) :
    RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    lateinit var clickedHolder: DogViewHolder

    private var dogResponseList: List<Dog> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dogResponseList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_item_dog, parent, false)
        val holder = DogViewHolder(view)
        holder.itemView.setOnClickListener {
            if (holder.absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                clickedHolder = holder
                onItemClicked.invoke(dogResponseList[holder.absoluteAdapterPosition])
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bind(dogResponseList[position])
        }
    }

    class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewItemDogBinding.bind(view)
        fun bind(dog: Dog) {
            binding.ivItemImageView.loadImage(dog.imageUrl)
            binding.tvItemTitle.text = dog.name
        }
    }

    fun updateData(newList: List<Dog>) {
        dogResponseList = if (dogResponseList.isEmpty()) {
            newList
        } else {
            dogResponseList + newList
        }
    }
}

