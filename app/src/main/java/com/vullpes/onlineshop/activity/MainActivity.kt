package com.vullpes.onlineshop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.vullpes.onlineshop.R
import com.vullpes.onlineshop.adapters.BrandAdapter
import com.vullpes.onlineshop.adapters.PopularAdapter
import com.vullpes.onlineshop.adapters.SLiderAdapter
import com.vullpes.onlineshop.databinding.ActivityIntroBinding
import com.vullpes.onlineshop.databinding.ActivityMainBinding
import com.vullpes.onlineshop.model.SliderModel
import com.vullpes.onlineshop.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        initBanner()
        initBrand()
        initPopular()

        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.menuChart.setOnClickListener{
                startActivity(Intent(this@MainActivity,CartActivity::class.java ))
        }
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.loadBanners()
        viewModel.banner.observe(this){ items ->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE
        }
    }

    private fun banners(images:List<SliderModel>){
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.apply {
            adapter = SLiderAdapter(images,binding.viewPagerSlider)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(compositePageTransformer)
        }

        if(images.size > 1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPagerSlider)
        }




    }

    private fun initBrand(){
        viewModel.loadBrand()
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this){ brands ->
            binding.progressBarBrand.visibility = View.GONE
            binding.viewBrand.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = BrandAdapter(brands)
            }
        }
    }

    private fun initPopular(){
        viewModel.loadPopular()
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.items.observe(this){ popular ->
            binding.progressBarPopular.visibility = View.GONE
            binding.viewPopular.apply {
                layoutManager = GridLayoutManager(this@MainActivity,  2)
                adapter = PopularAdapter(popular)
            }
        }
    }

}