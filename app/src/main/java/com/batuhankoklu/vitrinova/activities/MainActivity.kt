package com.batuhankoklu.vitrinova.activities

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.batuhankoklu.cotrack.Helpers.Helpers
import com.batuhankoklu.vitrinova.helpers.StaticVariables
import com.batuhankoklu.vitrinova.R
import com.batuhankoklu.vitrinova.adapters.*
import com.batuhankoklu.vitrinova.animations.DepthPageTransformer
import com.batuhankoklu.vitrinova.databinding.ActivityMainBinding
import com.batuhankoklu.vitrinova.model.*
import com.batuhankoklu.vitrinova.model.Collections
import com.batuhankoklu.vitrinova.viewmodels.MainActivityViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainActivityViewModel

    private var newProductsAdapter : NewProductsAdapter? = null
    private var newProducts = ArrayList<Product>()

    private var categoriesAdapter : CategoriesAdapter? = null
    private var categories = ArrayList<Category>()

    private var collectionsAdapter : CollectionsAdapter? = null
    private var collections = ArrayList<Collections>()

    private var editorShopsAdapter : EditorShopsAdapter? = null
    private var editorShops = ArrayList<EditorShops>()

    private var newShopsAdapter : NewShopsAdapter? = null
    private var newShops = ArrayList<NewShops>()

    private lateinit var viewPagerAdapter : ViewPagerAdapter

    var helper = Helpers()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDataBinding()

        initNewProductsRecyclerView()
        initCategoriesRecyclerView()
        initCollectionsRecyclerView()
        initEditorShopsRecyclerView()
        initNewShopsRecyclerView()

        initSwipeToRefresh()

        getDiscover()

        binding.imgSpeechToText.setOnClickListener {
            speechToTextView()
        }

        binding.txtAllNewProducts.setOnClickListener {
            StaticVariables.generalListSelection = 0
            helper.redirectFromTo(this , ListActivity::class.java , newProducts , "new_products")
        }

        binding.txtAllCollections.setOnClickListener {
            StaticVariables.generalListSelection = 1
            helper.redirectFromTo(this , ListActivity::class.java , collections , "collections")
        }

        binding.txtAllEditorShowcases.setOnClickListener {
            StaticVariables.generalListSelection = 2
            //Başka yöntem
            StaticVariables.editorShops = editorShops
            helper.redirectFromTo(this , ListActivity::class.java , null , "")
        }


    }

    private fun initDataBinding(){
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.lifecycleOwner = this
    }

    fun getDiscover(){
        //viewModel.isLoading.value = true
        binding.swipeLayout.isRefreshing = true
        viewModel.getDiscover()?.observe(this , Observer { baseResponse ->
            //viewModel.isLoading.value = false
            binding.swipeLayout.isRefreshing = false

            if (baseResponse != null){
                //Viewpager
                initViewPager(baseResponse.featured?.featured)

                //New Products
                newProducts = baseResponse.newProducts?.products ?: ArrayList()
                newProductsAdapter?.updateNewProducts(baseResponse.newProducts?.products ?: ArrayList())

                //Categories
                categories = baseResponse.categories?.categories ?: ArrayList()
                categoriesAdapter?.updateCategories(baseResponse.categories?.categories ?: ArrayList())

                //Collections
                collections = baseResponse.collections?.collections ?: ArrayList()
                collectionsAdapter?.updateCollections(baseResponse.collections?.collections ?: ArrayList())

                //Editor Shops
                editorShops = baseResponse.editorShops?.shops ?: ArrayList()
                editorShopsAdapter?.updateEditorShops(baseResponse.editorShops?.shops ?: ArrayList())
                editorShowcaseRecyclerViewSnapping(baseResponse.editorShops?.shops ?: ArrayList())

                //New Shops
                newShops = baseResponse.newShops?.shops ?: ArrayList()
                newShopsAdapter?.updateNewShops(baseResponse.newShops?.shops ?: ArrayList())
            }

        })
    }

    fun initNewProductsRecyclerView(){
        newProductsAdapter = NewProductsAdapter(newProducts)
        binding.rvNewProducts.setHasFixedSize(true)
        binding.rvNewProducts.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
        binding.rvNewProducts.adapter = newProductsAdapter
        binding.cnsNewProducts.visibility = View.VISIBLE
    }

    fun initCategoriesRecyclerView(){
        categoriesAdapter = CategoriesAdapter(categories)
        binding.rvCategories.setHasFixedSize(true)
        binding.rvCategories.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
        binding.rvCategories.adapter = categoriesAdapter
        binding.cnsCategories.visibility = View.VISIBLE
    }

    fun initCollectionsRecyclerView(){
        collectionsAdapter = CollectionsAdapter(collections)
        binding.rvCollections.setHasFixedSize(true)
        binding.rvCollections.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
        binding.rvCollections.adapter = collectionsAdapter
        binding.cnsCollections.visibility = View.VISIBLE
    }

    fun initEditorShopsRecyclerView(){
        editorShopsAdapter = EditorShopsAdapter(editorShops)
        binding.rvEditorShowcases.setHasFixedSize(true)
        binding.rvEditorShowcases.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
        binding.rvEditorShowcases.adapter = editorShopsAdapter
        binding.cnsEditorShowcases.visibility = View.VISIBLE
    }

    fun initNewShopsRecyclerView(){
        newShopsAdapter = NewShopsAdapter(newShops)
        binding.rvNewShowcases.setHasFixedSize(true)
        binding.rvNewShowcases.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
        binding.rvNewShowcases.adapter = newShopsAdapter
        binding.cnsNewShowcases.visibility = View.VISIBLE
    }

    fun initViewPager(featuredList : ArrayList<Featured>?){

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        if (!featuredList.isNullOrEmpty()){
            for (i in featuredList.indices){
                viewPagerAdapter.addFrag(
                    GeneralViewPager(featuredList.get(i).cover.url ,
                        featuredList.get(i).title ,
                        featuredList.get(i).subTitle),featuredList.get(i).title)

            }
        }

        binding.viewpager.adapter = viewPagerAdapter
        binding.cnsViewPagerConstraintLayout.visibility = View.VISIBLE

        binding.dotsIndicator.setViewPager(binding.viewpager)

        binding.viewpager.setPageTransformer(true , DepthPageTransformer())
    }

    fun editorShowcaseRecyclerViewSnapping(editorShopsList: ArrayList<EditorShops>){

        Handler().postDelayed(object : Runnable {
            override fun run() {
                Helpers().urlToImage(editorShopsList[0].cover.url, binding.cnsEditorShowcases , null , this@MainActivity)
            }

        },500)

        var snapHelper : SnapHelper = object : PagerSnapHelper() {
            override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager?, velocityX: Int, velocityY: Int): Int {
                var snapPosition =  super.findTargetSnapPosition(layoutManager, velocityX, velocityY)

                Log.e("TAGY", snapPosition.toString())
                Helpers().urlToImage(editorShopsList[snapPosition].cover.url, binding.cnsEditorShowcases , null , this@MainActivity)

                return snapPosition
            }
        }

        snapHelper.attachToRecyclerView(binding.rvEditorShowcases)

    }

    fun initSwipeToRefresh(){
        binding.swipeLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                Handler().postDelayed({
                    helper.refreshActivity(this@MainActivity)
                },1000)
            }
        })
    }

    companion object {
        const val REQUEST_CODE_SPEECH = 1
    }
    fun speechToTextView(){


            if(Helpers().checkPermissions(this)){
                val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now!")
                try {
                    startActivityForResult(speechIntent, REQUEST_CODE_SPEECH)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, "Cihazınız bu özelliği desteklemiyor", Toast.LENGTH_LONG).show()
                }
            }else{
                Helpers().requestPermissions(this)
            }

    }

    //Perm istek sonucu
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_SPEECH) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Granted. Start getting the location information
                speechToTextView()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_SPEECH -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    if (!result.isNullOrEmpty()) {
                        val recognizedText = result[0]
                        binding.etxtSearch.setText(recognizedText)
                    }
                }
            }
        }
    }

}