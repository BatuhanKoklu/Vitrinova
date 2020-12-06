package com.batuhankoklu.vitrinova.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.batuhankoklu.vitrinova.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class BindingAdapter {

    companion object {
        @JvmStatic // -> Required DataBindingComponent is null in class AgentDetailsActivityBindingImpl hatasının çözümü
        @BindingAdapter("android:imageURL")
        fun setImageUrl(imageView : ImageView , URL : String?){
            try {
                imageView.alpha = 0f
                Picasso.get().load(URL).placeholder(R.mipmap.ic_vitrinova).error(R.mipmap.ic_vitrinova).noFade().fit().centerCrop().into(imageView , object : Callback {
                    override fun onSuccess() {
                        imageView.animate().setDuration(300).alpha(1f).start()
                    }

                    override fun onError(e: Exception?) {

                    }

                })
            }catch (e : Exception){

            }

        }


    }

}