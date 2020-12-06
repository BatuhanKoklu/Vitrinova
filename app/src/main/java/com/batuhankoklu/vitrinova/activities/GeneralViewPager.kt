package com.batuhankoklu.vitrinova.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.batuhankoklu.cotrack.Helpers.Helpers
import com.batuhankoklu.vitrinova.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class GeneralViewPager(imageUrl : String? , featuredTitle : String , featuredDetail : String) : Fragment() {

    var helper = Helpers()
    var imageUrl = imageUrl
    var featuredTitle = featuredTitle
    var featuredDetail = featuredDetail

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater.inflate(R.layout.general_view_pager,container,false)

        var imgGeneralImage : ImageView = v.findViewById<ImageView>(R.id.imgGeneralImage)

        Picasso.get().load(imageUrl).noFade().fit().centerCrop().into(imgGeneralImage , object : Callback {
            override fun onSuccess() {
                imgGeneralImage.animate().setDuration(300).alpha(1f).start()
            }

            override fun onError(e: Exception?) {
                Log.e("tagy" , "image load fail")
            }

        })

        var txtFeaturedTitle : TextView = v.findViewById(R.id.txtFeaturedTitle)
        var txtFeaturedDetail : TextView = v.findViewById(R.id.txtFeaturedDetail)

        txtFeaturedTitle.text = featuredTitle
        txtFeaturedDetail.text = featuredDetail



        return v
    }



    companion object {

        fun newInstance() : GeneralViewPager = GeneralViewPager(null , "" , "")
    }
}