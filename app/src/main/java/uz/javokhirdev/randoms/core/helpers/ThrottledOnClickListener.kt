package uz.javokhirdev.randoms.core.helpers

import android.os.SystemClock
import android.view.View
import java.util.*

class ThrottledOnClickListener(private val onClickAction: () -> Unit) :
    View.OnClickListener {

    private val lastClickMap: MutableMap<View, Long> = WeakHashMap()

    override fun onClick(view: View) {
        val previousClickTimestamp = lastClickMap[view]
        val currentTimestamp = SystemClock.uptimeMillis()

        lastClickMap[view] = currentTimestamp

        if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > 500L) {
            onClickAction()
        }
    }
}