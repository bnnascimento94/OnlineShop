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
import com.vullpes.onlineshop.databinding.ViewholderColorBinding
import com.vullpes.onlineshop.databinding.ViewholderSizeBinding


class SizeAdapter(private val items: List<String>):
    RecyclerView.Adapter<SizeAdapter.ViewHolder>() {


    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ViewholderSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {


        holder.binding.sizeTxt.text = items[position]

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition == position){
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
            holder.binding.sizeTxt.setTextColor(holder.binding.root.context.resources.getColor(R.color.purple))
        }else{
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeTxt.setTextColor(holder.binding.root.context.resources.getColor(R.color.black))

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val binding: ViewholderSizeBinding): RecyclerView.ViewHolder(binding.root) {





    }
}