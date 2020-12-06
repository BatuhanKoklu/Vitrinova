package com.batuhankoklu.vitrinova.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.batuhankoklu.vitrinova.R
import com.batuhankoklu.vitrinova.databinding.CellNewProductsBinding
import com.batuhankoklu.vitrinova.model.Product

class NewProductsAdapter(newProducts : ArrayList<Product> , matchParentWidth : Boolean = false) : RecyclerView.Adapter<NewProductsAdapter.NewProductsViewHolder>() {

    var layoutInflater : LayoutInflater? = null
    var newProducts = newProducts
    var matchParentWidth = matchParentWidth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewProductsViewHolder {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.context)
        }
        var newProductsBinding : CellNewProductsBinding = DataBindingUtil
            .inflate(layoutInflater!! , R.layout.cell_new_products , parent , false)

        return  NewProductsViewHolder(newProductsBinding , matchParentWidth)
    }

    override fun getItemCount(): Int {
        return newProducts.count()
    }

    override fun onBindViewHolder(holder: NewProductsViewHolder, position: Int) {
        holder.bindNewProduct(newProducts.get(position))
    }

    fun updateNewProducts(newProductList : ArrayList<Product>){
        newProducts.clear()
        newProducts.addAll(newProductList)
        notifyDataSetChanged()
    }


    class NewProductsViewHolder(cellNewProductsBinding: CellNewProductsBinding , matchParentWidth : Boolean) : RecyclerView.ViewHolder(cellNewProductsBinding.root){

        //Auto generated -> CellNewProductsBinding
        var cellNewProductsBinding : CellNewProductsBinding = cellNewProductsBinding
        var matchParentWidth = matchParentWidth


        fun bindNewProduct(newProduct : Product){
            if(matchParentWidth){
                cellNewProductsBinding.newProductsCellParent.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            }
            cellNewProductsBinding.txtOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            cellNewProductsBinding.newProduct = newProduct
            cellNewProductsBinding.executePendingBindings()
        }

    }

}