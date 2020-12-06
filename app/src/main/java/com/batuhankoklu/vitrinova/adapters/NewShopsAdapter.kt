package com.batuhankoklu.vitrinova.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.batuhankoklu.vitrinova.R
import com.batuhankoklu.vitrinova.databinding.CellNewShopsBinding
import com.batuhankoklu.vitrinova.model.NewShops

class NewShopsAdapter(newShops : ArrayList<NewShops>) : RecyclerView.Adapter<NewShopsAdapter.NewShopsViewHolder>() {

    var layoutInflater : LayoutInflater? = null
    var newShops = newShops

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewShopsViewHolder {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.context)
        }
        var newShopsBinding : CellNewShopsBinding = DataBindingUtil
            .inflate(layoutInflater!! , R.layout.cell_new_shops , parent , false)

        return NewShopsViewHolder(newShopsBinding)
    }

    override fun getItemCount(): Int {
        return newShops.count()
    }

    override fun onBindViewHolder(holder: NewShopsViewHolder, position: Int) {
        holder.bindnewShops(newShops.get(position))
    }

    fun updateNewShops(newList : ArrayList<NewShops>){
        newShops.clear()
        newShops.addAll(newList)
        notifyDataSetChanged()
    }


    class NewShopsViewHolder(cellnewShopsBinding: CellNewShopsBinding) : RecyclerView.ViewHolder(cellnewShopsBinding.root){

        //Auto generated -> CellnewShopsBinding
        var cellnewShopsBinding : CellNewShopsBinding = cellnewShopsBinding

        fun bindnewShops(newShop : NewShops){
            cellnewShopsBinding.newShop = newShop
            cellnewShopsBinding.executePendingBindings()
        }

    }

}