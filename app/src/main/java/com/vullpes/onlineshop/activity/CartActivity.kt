package com.vullpes.onlineshop.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.vullpes.onlineshop.R
import com.vullpes.onlineshop.adapters.CartAdapter
import com.vullpes.onlineshop.databinding.ActivityCartBinding
import com.vullpes.onlineshop.databinding.ViewholderCartBinding
import com.vullpes.onlineshop.helper.ChangeNumberItemsListener
import com.vullpes.onlineshop.helper.ManagmentCart

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double =0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        initCartList()
        calculateCart()
    }

    private fun initCartList() {
        binding.viewCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.viewCart.adapter = CartAdapter(managmentCart.getListCart(),this, object:ChangeNumberItemsListener {
            override fun onChanged() {
                calculateCart()
            }


        })

        with(binding){
            emptyTxt.visibility = if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility= if(managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun calculateCart(){
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((managmentCart.getTotalFee()+tax+delivery) *100)/100

        val itemTotal = Math.round((managmentCart.getTotalFee()) *100)/100
        with(binding){
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text= "$$total"
        }

        with(binding){
            emptyTxt.visibility = if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility= if(managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener{
            finish()
        }
    }
}