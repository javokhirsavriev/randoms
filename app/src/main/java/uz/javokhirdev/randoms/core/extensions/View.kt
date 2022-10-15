package uz.javokhirdev.randoms.core.extensions

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import uz.javokhirdev.randoms.core.helpers.ThrottledOnClickListener

fun View.onClick(onClickAction: () -> Unit) {
    setOnClickListener(ThrottledOnClickListener(onClickAction))
}

fun View.beVisibleIf(isVisible: Boolean) = if (isVisible) beVisible() else beGone()

fun View.beVisible() {
    isVisible = true
}

fun View.beGone() {
    isVisible = false
}

fun View.insetsPadding(padding: Int = 0) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updatePadding(
            left = insets.left + context.px(padding),
            top = insets.top + context.px(padding),
            right = insets.right + context.px(padding),
            bottom = insets.bottom + context.px(padding)
        )
        WindowInsetsCompat.CONSUMED
    }
}