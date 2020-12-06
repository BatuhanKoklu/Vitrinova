package com.batuhankoklu.vitrinova.network

import com.batuhankoklu.vitrinova.model.*
import com.google.gson.Gson
import org.json.JSONArray

open class AssignModels {

    fun getDiscoverModel(jsonArray : JSONArray) : BaseResponse{

        var featured : FeaturedResponse? = null
        var newProducts : NewProductsResponse? = null
        var categories : CategoriesResponse? = null
        var collections : CollectionsResponse? = null
        var editorShops : EditorShopsResponse? = null
        var newShops : NewShopsResponse? = null

        for (i in 0 until jsonArray.length()){

            //arrayin i. elemanı
            var jsonAddressObject = jsonArray.getJSONObject(i)

            var type = jsonAddressObject.getString("type")
            var title = jsonAddressObject.getString("title")
            if(type == "featured"){
                var featuredList = ArrayList<Featured>()
                var featuredJsonList = jsonAddressObject.getJSONArray("featured")
                for (i in 0 until featuredJsonList.length()){
                    var featured = Gson().fromJson(featuredJsonList[i].toString(), Featured::class.java)
                    featuredList.add(featured)
                }
                featured = FeaturedResponse(type , title , featuredList)
            }
            else if(type == "new_products"){
                var newProductList = ArrayList<Product>()
                var newProductsJsonArray = jsonAddressObject.getJSONArray("products")
                for (i in 0 until newProductsJsonArray.length()){
                    var product = Gson().fromJson(newProductsJsonArray[i].toString() , Product::class.java)
                    newProductList.add(product)
                }
                newProducts = NewProductsResponse(type , title , newProductList)
            }
            else if(type == "categories"){
                var categoriesList = ArrayList<Category>()
                var categoriesJsonArray = jsonAddressObject.getJSONArray("categories")
                for (i in 0 until categoriesJsonArray.length()){
                    var category = Gson().fromJson(categoriesJsonArray[i].toString() , Category::class.java)
                    categoriesList.add(category)
                }
                categories = CategoriesResponse(type , title , categoriesList)
            }
            else if(type == "collections"){
                var collectionsList = ArrayList<Collections>()
                var collectionsJsonArray = jsonAddressObject.getJSONArray("collections")
                for (i in 0 until collectionsJsonArray.length()){
                    var collection = Gson().fromJson(collectionsJsonArray[i].toString() , Collections::class.java)
                    collectionsList.add(collection)
                }
                collections = CollectionsResponse(type , title , collectionsList)
            }
            else if(type == "editor_shops"){
                var editorShopsList = ArrayList<EditorShops>()
                var editorShopsJsonArray = jsonAddressObject.getJSONArray("shops")
                for (i in 0 until editorShopsJsonArray.length()){
                    var editorShop = Gson().fromJson(editorShopsJsonArray[i].toString() , EditorShops::class.java)
                    editorShopsList.add(editorShop)
                }
                editorShops = EditorShopsResponse(type , title , editorShopsList)
            }
            else if(type == "new_shops"){
                var newShopsList = ArrayList<NewShops>()
                var newShopsJsonArray = jsonAddressObject.getJSONArray("shops")
                for (i in 0 until newShopsJsonArray.length()){
                    var newShop = Gson().fromJson(newShopsJsonArray[i].toString() , NewShops::class.java)
                    newShopsList.add(newShop)
                }
                newShops = NewShopsResponse(type , title , newShopsList)
            }

        }//end of for

        return BaseResponse(featured , newProducts , categories , collections , editorShops , newShops)

    }


}