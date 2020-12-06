package com.batuhankoklu.vitrinova.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class BaseResponse(
    var featured : FeaturedResponse?,
    var newProducts : NewProductsResponse?,
    var categories : CategoriesResponse?,
    var collections : CollectionsResponse?,
    var editorShops : EditorShopsResponse?,
    var newShops : NewShopsResponse?
)

@Parcelize
data class FeaturedResponse(
    @SerializedName("type") var type : String,
    @SerializedName("title") var title : String,
    @SerializedName("featured") var featured : ArrayList<Featured>
): Parcelable

@Parcelize
data class Featured(
    @SerializedName("id") var id : Int,
    @SerializedName("type") var type : String,
    @SerializedName("cover") var cover : Cover,
    @SerializedName("title") var title : String,
    @SerializedName("sub_title") var subTitle : String
): Parcelable

@Parcelize
data class Cover(
    @SerializedName("url") var url : String
): Parcelable

@Parcelize
data class NewProductsResponse(
    @SerializedName("type") var type : String,
    @SerializedName("title") var title : String,
    @SerializedName("products") var products : ArrayList<Product>
): Parcelable

@Parcelize
data class Product(
    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String,
    @SerializedName("old_price") var oldPrice : Int,
    @SerializedName("price") var price : Int,
    @SerializedName("shop") var shop : Shop,
    @SerializedName("images") var images : ArrayList<Cover>
) : Parcelable

@Parcelize
data class Shop(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String
): Parcelable

@Parcelize
data class CategoriesResponse(
    @SerializedName("type") var type : String,
    @SerializedName("title") var title : String,
    @SerializedName("categories") var categories : ArrayList<Category>
): Parcelable

@Parcelize
data class Category(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("cover") var cover : Cover,
    @SerializedName("logo") var logo : Logo
): Parcelable

@Parcelize
data class CollectionsResponse(
    @SerializedName("type") var type : String,
    @SerializedName("title") var title : String,
    @SerializedName("collections") var collections : ArrayList<Collections>
): Parcelable

@Parcelize
data class Collections(
    @SerializedName("id") var id : String,
    @SerializedName("title") var title : String,
    @SerializedName("definition") var definition : String,
    @SerializedName("cover") var cover : Cover
): Parcelable

@Parcelize
data class EditorShopsResponse(
    @SerializedName("type") var type : String,
    @SerializedName("title") var title : String,
    @SerializedName("shops") var shops : ArrayList<EditorShops>
): Parcelable

@Parcelize
data class EditorShops(
    @SerializedName("id") var id : String,
    @SerializedName("name") var name : String,
    @SerializedName("definition") var definition : String,
    @SerializedName("popular_products") var popularProducts : ArrayList<Product>,
    @SerializedName("cover") var cover : Cover,
    @SerializedName("logo") var logo : Logo
): Parcelable

@Parcelize
data class NewShopsResponse(
    @SerializedName("type") var type : String,
    @SerializedName("title") var title : String,
    @SerializedName("shops") var shops : ArrayList<NewShops>
): Parcelable

@Parcelize
data class NewShops(
    @SerializedName("id") var id : String,
    @SerializedName("name") var name : String,
    @SerializedName("definition") var definition : String,
    @SerializedName("product_count") var productCount : Int,
    @SerializedName("cover") var cover : Cover
): Parcelable

@Parcelize
data class Logo(
    @SerializedName("url") var url : String,
    @SerializedName("medium") var medium: Medium
): Parcelable

@Parcelize
data class Medium(
    @SerializedName("url") var url : String
): Parcelable


