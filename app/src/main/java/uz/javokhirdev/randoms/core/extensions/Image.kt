package uz.javokhirdev.randoms.core.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(null)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.imageRes(res: Int? = null) = res?.let { setImageResource(it) }

fun ImageView.imageTint(color: Int) {
    imageTintList = ContextCompat.getColorStateList(context, color)
}