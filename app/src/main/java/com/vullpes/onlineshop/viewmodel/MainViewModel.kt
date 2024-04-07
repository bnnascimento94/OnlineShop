package com.vullpes.onlineshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.vullpes.onlineshop.model.BrandModel
import com.vullpes.onlineshop.model.ItemModel
import com.vullpes.onlineshop.model.SliderModel

class MainViewModel: ViewModel() {

    private val firebaseDatabase: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val _banner = MutableLiveData<List<SliderModel>>()
    val banner: LiveData<List<SliderModel>> = _banner

    private val _brand = MutableLiveData<List<BrandModel>>()
    val brands: LiveData<List<BrandModel>> = _brand

    private val _items = MutableLiveData<List<ItemModel>>()
    val items: LiveData<List<ItemModel>> = _items



    fun loadBanners(){
        firebaseDatabase.getReference("Banner").apply {
            addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val lists = snapshot.children.mapNotNull {
                        it.getValue(SliderModel::class.java)
                    }

                    _banner.value = lists

                }
                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }

            })
        }

    }


    fun loadBrand(){
        firebaseDatabase.getReference("Category").apply {
            addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val lists = snapshot.children.mapNotNull {
                        it.getValue(BrandModel::class.java)
                    }
                    _brand.value = lists
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }

            })
        }
    }

    fun loadPopular(){
        firebaseDatabase.getReference("Items").apply {
            addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val lists = snapshot.children.mapNotNull {
                        it.getValue(ItemModel::class.java)
                    }
                    _items.value = lists
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }

            })
        }
    }




}
