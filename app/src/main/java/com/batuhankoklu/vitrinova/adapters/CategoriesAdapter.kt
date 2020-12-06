package com.batuhankoklu.vitrinova.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.batuhankoklu.vitrinova.R
import com.batuhankoklu.vitrinova.databinding.CellCategoriesBinding
import com.batuhankoklu.vitrinova.model.Category

class CategoriesAdapter(categories : ArrayList<Category>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    var layoutInflater : LayoutInflater? = null
    var categories = categories

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.context)
        }
        var categoriesBinding : CellCategoriesBinding = DataBindingUtil
            .inflate(layoutInflater!! , R.layout.cell_categories , parent , false)

        return  CategoriesViewHolder(categoriesBinding)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bindNewCategory(categories.get(position))
    }

    fun updateCategories(categoryList : ArrayList<Category>){
        categories.clear()
        categories.addAll(categoryList)
        notifyDataSetChanged()
    }


    class CategoriesViewHolder(cellcategoriesBinding: CellCategoriesBinding) : RecyclerView.ViewHolder(cellcategoriesBinding.root){

        //Auto generated -> CellcategoriesBinding
        var cellcategoriesBinding : CellCategoriesBinding = cellcategoriesBinding

        fun bindNewCategory(category : Category){
            cellcategoriesBinding.category = category
            cellcategoriesBinding.executePendingBindings()
        }

    }

}