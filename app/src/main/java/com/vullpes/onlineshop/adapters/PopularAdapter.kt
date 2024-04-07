package com.vullpes.onlineshop.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.vullpes.onlineshop.activity.DetailActivity
import com.vullpes.onlineshop.databinding.ViewholderRecomendedBinding
import com.vullpes.onlineshop.model.ItemModel

class PopularAdapter(private val popularItems: List<ItemModel>): RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ViewholderRecomendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return popularItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(popularItems[position])
    }


    class ViewHolder(val binding: ViewholderRecomendedBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(itemModel: ItemModel){
            binding.apply {
                val requestOptions = RequestOptions().transform(CenterCrop())
                Glide.with(binding.root).load(itemModel.picUrl[0]).apply(requestOptions).into(binding.pic)
                priceTxt.text = "$${itemModel.price.toString()}"
                ratingTxt.text = itemModel.rating.toString()
                titleTxt.text = itemModel.title

                this.itemView.setOnClickListener {
                    val intent = Intent(binding.root.context, DetailActivity::class.java)
                    intent.putExtra("object", itemModel)
                    binding.root.context.startActivity(intent)
                }


            }
        }
    }




}