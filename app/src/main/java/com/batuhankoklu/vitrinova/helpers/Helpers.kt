package com.batuhankoklu.cotrack.Helpers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.batuhankoklu.vitrinova.activities.MainActivity.Companion.REQUEST_CODE_SPEECH
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.Serializable
import java.lang.Exception

class Helpers {

    fun <T> redirectFromTo(context: Context, to: Class<*>, data: T? = null, name: String = ""){
        var intent = Intent(context,to)
        if (data != null){
            intent.putExtra(name , data as Serializable)
        }
        context.startActivity(intent)

    }

    fun intToDp(context: Context , value : Int) : Int{
        return (value * context!!.resources.displayMetrics.density).toInt()
    }

    fun urlToImage(url: String?, imageView: ImageView , defaulImage : Drawable? , context: Context? , type : Int = -1) {

        try {
            Picasso.get()
                .load(url)
                //.placeholder(R.mipmap.img_icon_profile)
                //.error(R.mipmap.img_icon_profile)
                .into(imageView)


        }
        catch (ex : Exception){
            if(type == 0){
                imageView.layoutParams.height = intToDp(context!!, 32)
                imageView.layoutParams.width  = intToDp(context!!, 32)
                imageView.requestLayout()
            }

            imageView.setImageDrawable(defaulImage)

        }
    }

    fun urlToImage(url: String?, constraintLayout: ConstraintLayout , defaulImage : Drawable? , context: Context? , type : Int = -1) {

        try {
            Picasso.get()
                .load(url)
                //.placeholder(R.mipmap.img_icon_profile)
                //.error(R.mipmap.img_icon_profile)
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        Log.d("TAG", "Prepare Load");
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        Log.d("TAG", "FAILED");
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        constraintLayout.background = BitmapDrawable(context!!.resources , bitmap)
                    }

                })

        }
        catch (ex : Exception){

        }
    }

    //Perm kontrolü
    fun checkPermissions(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
                return true
            }
        }
        return false
    }


    //Perm istek
    fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_CODE_SPEECH)
    }

    fun refreshActivity(activity: Activity){
        activity.finish()
        activity.overridePendingTransition(0,0)
        activity.startActivity(activity.intent)
        activity.overridePendingTransition(0,0)
    }

    fun pxToDp(px : Int) : Int{
        return ((px / Resources.getSystem().displayMetrics.density).toInt())
    }

}