package uz.javokhirdev.randoms.core.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun Context.primaryColor(): Int {
    val value = TypedValue()
    theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, value, true)
    return value.data
}

fun ImageView.loadImage(
    url: String?,
    withProgress: Boolean = true,
    skipMemoryCache: Boolean = false
) {
    val progressDrawable = CircularProgressDrawable(context)
    progressDrawable.strokeWidth = 10f
    progressDrawable.centerRadius = 60f
    progressDrawable.setColorSchemeColors(context.primaryColor())
    progressDrawable.start()

    Glide.with(this)
        .load(url)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.ALL)
        .skipMemoryCache(skipMemoryCache)
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(if (withProgress) progressDrawable else null)
        .into(this)
}

fun ImageView.imageRes(res: Int? = null) = res?.let { setImageResource(it) }

fun ImageView.imageTint(color: Int) {
    imageTintList = ContextCompat.getColorStateList(context, color)
}