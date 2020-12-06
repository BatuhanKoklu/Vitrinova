package com.batuhankoklu.vitrinova.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.batuhankoklu.vitrinova.helpers.StaticVariables
import com.batuhankoklu.vitrinova.R
import com.batuhankoklu.vitrinova.adapters.CollectionsAdapter
import com.batuhankoklu.vitrinova.adapters.EditorShopsAdapter
import com.batuhankoklu.vitrinova.adapters.NewProductsAdapter
import com.batuhankoklu.vitrinova.databinding.ActivityListBinding
import com.batuhankoklu.vitrinova.model.Collections
import com.batuhankoklu.vitrinova.model.EditorShops
import com.batuhankoklu.vitrinova.model.Product

class ListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListBinding

    private var newProductsAdapter : NewProductsAdapter? = null
    private var newProducts = ArrayList<Product>()

    private var collectionsAdapter : CollectionsAdapter? = null
    private var collections = ArrayList<Collections>()

    private var editorShopsAdapter : EditorShopsAdapter? = null
    private var editorShops = ArrayList<EditorShops>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initDataBinding()

        if(StaticVariables.generalListSelection == 0){
            initNewProductsRecyclerView()
        }
        else if (StaticVariables.generalListSelection == 1){
            initCollectionsRecyclerView()
        }
        else if (StaticVariables.generalListSelection == 2){
            initEditorShopsRecyclerView()
        }



    }

    private fun initDataBinding(){
        binding = DataBindingUtil.setContentView(this , R.layout.activity_list)
        binding.lifecycleOwner = this
    }

    fun initNewProductsRecyclerView(){
        newProducts = intent.extras?.get("new_products") as ArrayList<Product>
        newProductsAdapter = NewProductsAdapter(newProducts , true)
        binding.rvGeneral.setHasFixedSize(true)
        binding.rvGeneral.layoutManager = GridLayoutManager(this , 2)
        binding.rvGeneral.adapter = newProductsAdapter
    }

    fun initCollectionsRecyclerView(){
        collections = intent.extras?.get("collections") as ArrayList<Collections>
        collectionsAdapter = CollectionsAdapter(collections , true)
        binding.rvGeneral.setHasFixedSize(true)
        binding.rvGeneral.layoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false)
        binding.rvGeneral.adapter = collectionsAdapter
    }

    fun initEditorShopsRecyclerView(){
        editorShops = StaticVariables.editorShops
        editorShopsAdapter = EditorShopsAdapter(editorShops , true)
        binding.rvGeneral.setHasFixedSize(true)
        binding.rvGeneral.layoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false)
        binding.rvGeneral.adapter = editorShopsAdapter
    }


}