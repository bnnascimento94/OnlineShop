package com.vullpes.onlineshop.activity


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.vullpes.onlineshop.R
import com.vullpes.onlineshop.adapters.ColorAdapter
import com.vullpes.onlineshop.adapters.SLiderAdapter
import com.vullpes.onlineshop.adapters.SizeAdapter
import com.vullpes.onlineshop.databinding.ActivityDetailBinding
import com.vullpes.onlineshop.databinding.ActivityMainBinding
import com.vullpes.onlineshop.databinding.ViewholderSizeBinding
import com.vullpes.onlineshop.helper.ManagmentCart
import com.vullpes.onlineshop.model.ItemModel
import com.vullpes.onlineshop.model.SliderModel
import java.util.ResourceBundle.getBundle

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemModel
    private var numberOder = 1
    private lateinit var managementCart: ManagmentCart



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        getBundle()
        banners()
        initLists()

    }

    private fun initLists() {
        binding.sizeList.apply {
            adapter = SizeAdapter(item.size)
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.colorList.apply {
            adapter = ColorAdapter(item.picUrl){position ->
                binding.slider.setCurrentItem(position, true)

            }
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)

        }

    }

    private fun banners() {
        val sliderItems = item.picUrl.map {urlPic ->
            SliderModel(urlPic)
        }

        binding.slider.adapter = SLiderAdapter(sliderItems,binding.slider)
        binding.slider.apply {
            adapter = SLiderAdapter(sliderItems,this)
            clipToPadding = true
            clipChildren = true
            offscreenPageLimit = 3
        }

        if(sliderItems.size > 1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.slider)
        }

    }
    private fun getBundle() {

        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$ ${item.price}"
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.addToChartBtn.setOnClickListener {
            item.numberInCart = numberOder
            managementCart.insertFood(item)

        }
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.chartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CartActivity::class.java))
        }





    }
}