package com.vullpes.onlineshop.adapters

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.vullpes.onlineshop.R
import com.vullpes.onlineshop.databinding.SliderItemConteinerBinding
import com.vullpes.onlineshop.databinding.ViewholderBrandBinding
import com.vullpes.onlineshop.model.BrandModel

class BrandAdapter(private val items: List<BrandModel>):
    RecyclerView.Adapter<BrandAdapter.ViewHolder>() {


    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ViewholderBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.title.text = items[position].title
        val requestOptions = RequestOptions().transform(CenterInside())
        Glide.with(holder.binding.root).load(items[position].picUrl).apply(requestOptions).into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        holder.binding.title.setTextColor(holder.binding.root.context.resources.getColor(R.color.white, null))
        if(selectedPosition == position){
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(holder.binding.root.context.getColor(R.color.white)))
            holder.binding.pic.contentDescription = items[position].title

            holder.binding.title.visibility = View.VISIBLE
        }else{
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(holder.binding.root.context.getColor(R.color.black)))
            holder.binding.pic.contentDescription = items[position].title

            holder.binding.title.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val binding: ViewholderBrandBinding): RecyclerView.ViewHolder(binding.root) {





    }
}