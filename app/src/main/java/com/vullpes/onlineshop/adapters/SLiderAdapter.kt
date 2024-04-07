package com.vullpes.onlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.vullpes.onlineshop.R
import com.vullpes.onlineshop.databinding.SliderItemConteinerBinding
import com.vullpes.onlineshop.model.SliderModel

class SLiderAdapter(private var sliderItems:List<SliderModel>, private val viewPager2: ViewPager2):
    RecyclerView.Adapter<SLiderAdapter.ViewHolder>() {

    private val runnable = Runnable {
        sliderItems = sliderItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = SliderItemConteinerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
       return sliderItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sliderItems[position])
        if(position == sliderItems.lastIndex -1){
            viewPager2.post(runnable)
        }
    }

    class ViewHolder(private val binding: SliderItemConteinerBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(sliderModel: SliderModel){
            val requestOptions = RequestOptions().transform(CenterInside())
            Glide.with(binding.root).load(sliderModel.url).apply(requestOptions).into(binding.imageSlide)
        }
    }


}