package com.batuhankoklu.vitrinova.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.batuhankoklu.vitrinova.R
import com.batuhankoklu.vitrinova.databinding.CellEditorShopsBinding
import com.batuhankoklu.vitrinova.model.EditorShops

class EditorShopsAdapter(editorShops : ArrayList<EditorShops> , matchParentWidth : Boolean = false) : RecyclerView.Adapter<EditorShopsAdapter.EditorShopsViewHolder>() {

    var layoutInflater : LayoutInflater? = null
    var editorShops = editorShops
    var matchParentWidth = matchParentWidth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditorShopsViewHolder {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.context)
        }
        var editorShopsBinding : CellEditorShopsBinding = DataBindingUtil
            .inflate(layoutInflater!! , R.layout.cell_editor_shops , parent , false)

        return EditorShopsViewHolder(editorShopsBinding , matchParentWidth)
    }

    override fun getItemCount(): Int {
        return editorShops.count()
    }

    override fun onBindViewHolder(holder: EditorShopsViewHolder, position: Int) {
        holder.bindEditorShops(editorShops.get(position))
    }

    fun updateEditorShops(editorList : ArrayList<EditorShops>){
        editorShops.clear()
        editorShops.addAll(editorList)
        notifyDataSetChanged()
    }


    class EditorShopsViewHolder(cellEditorShopsBinding: CellEditorShopsBinding , matchParentWidth : Boolean) : RecyclerView.ViewHolder(cellEditorShopsBinding.root){

        //Auto generated -> CellEditorShopsBinding
        var celleditorShopsBinding : CellEditorShopsBinding = cellEditorShopsBinding
        var matchParentWidth = matchParentWidth

        fun bindEditorShops(editorShop : EditorShops){
            if(matchParentWidth){
                celleditorShopsBinding.cnsEditorShopsParent.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            }
            celleditorShopsBinding.editorShop = editorShop
            celleditorShopsBinding.executePendingBindings()
        }

    }

}