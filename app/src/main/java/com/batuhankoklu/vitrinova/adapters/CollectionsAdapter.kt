package com.batuhankoklu.vitrinova.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.batuhankoklu.vitrinova.R
import com.batuhankoklu.vitrinova.databinding.CellCollectionsBinding
import com.batuhankoklu.vitrinova.model.Collections

class CollectionsAdapter(collections : ArrayList<Collections>, matchParentWidth : Boolean = false) : RecyclerView.Adapter<CollectionsAdapter.CollectionsViewHolder>() {

    var layoutInflater : LayoutInflater? = null
    var collections = collections
    var matchParentWidth = matchParentWidth


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.context)
        }
        var collectionsBinding : CellCollectionsBinding = DataBindingUtil
            .inflate(layoutInflater!! , R.layout.cell_collections , parent , false)

        return  CollectionsViewHolder(collectionsBinding , matchParentWidth)
    }

    override fun getItemCount(): Int {
        return collections.count()
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        holder.bindNewCollection(collections.get(position))
    }

    fun updateCollections(collectionList : ArrayList<Collections>){
        collections.clear()
        collections.addAll(collectionList)
        notifyDataSetChanged()
    }


    class CollectionsViewHolder(cellCollectionsBinding: CellCollectionsBinding , matchParentWidth : Boolean) : RecyclerView.ViewHolder(cellCollectionsBinding.root){

        //Auto generated -> CellCollectionsBinding
        var cellCollectionsBinding : CellCollectionsBinding = cellCollectionsBinding
        var matchParentWidth = matchParentWidth


        fun bindNewCollection(collection : Collections){
            if(matchParentWidth){
                cellCollectionsBinding.cnsCollectionsParent.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            }
            cellCollectionsBinding.collection = collection
            cellCollectionsBinding.executePendingBindings()
        }

    }

}