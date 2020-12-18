package com.kyle.core

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation

open class GlideImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : AppCompatImageView(context, attrs) {
    private var placeHolderPic: Drawable? = null
    private var errorPic: Drawable? = null
    private var imageUrl: Any? = null
    fun setImageUri(uri: Any?) {
        setImageUri(uri, null)
    }

    fun setImageCircleUri(uri: Any?) {
        val mRequestOptions = RequestOptions.circleCropTransform() //不做内存缓存
        setImageUri(uri, mRequestOptions)
    }

    fun setImageCornerUri(uri: Any?, radiusDp: Int) {
        val radius = (resources.displayMetrics.density * radiusDp + 0.5f).toInt()
        setImageUri(uri, RequestOptions.bitmapTransform(RoundedCorners(radius)))
    }

    fun setImageUri(uri: Any?, options: RequestOptions?) {
        var request: RequestBuilder<*> = Glide.with(context).load(uri)
        if (options != null) {
            request = request.apply(options)
        }
        if (placeHolderPic != null) {
            request = request.placeholder(placeHolderPic)
        }
        if (errorPic != null) {
            request = request.error(errorPic)
        }
        request.into(this)
    }

    fun setBlurUri(uri: Any?, radiusDp: Int, scale: Int) {
        setImageUri(uri, RequestOptions.bitmapTransform(BlurTransformation(radiusDp, scale)))
    }

    init {
        if (attrs != null) {
            @SuppressLint("Recycle") val a = context.obtainStyledAttributes(attrs, R.styleable.GlideImageView)
            placeHolderPic = a.getDrawable(R.styleable.GlideImageView_placeHolderPic)
            errorPic = a.getDrawable(R.styleable.GlideImageView_errorPic)
            imageUrl = a.getDrawable(R.styleable.GlideImageView_imageUrl)
        }
        if (imageUrl != null) {
            setImageUri(imageUrl)
        }
    }
}