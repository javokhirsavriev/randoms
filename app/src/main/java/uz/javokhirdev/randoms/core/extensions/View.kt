package uz.javokhirdev.randoms.core.extensions

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import uz.javokhirdev.randoms.core.helpers.ThrottledOnClickListener

fun View.onClick(onClickAction: () -> Unit) {
    setOnClickListener(ThrottledOnClickListener(onClickAction))
}

fun View.backTint(color: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, color)
}

fun View.backRes(res: Int) = setBackgroundResource(res)

fun View.beVisibleIf(isVisible: Boolean) = if (isVisible) beVisible() else beGone()

fun View.beVisible() {
    isVisible = true
}

fun View.beGone() {
    isVisible = false
}